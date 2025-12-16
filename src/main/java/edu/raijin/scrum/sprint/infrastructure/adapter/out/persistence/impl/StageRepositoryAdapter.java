package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.domain.port.persistence.FindStagePort;
import edu.raijin.scrum.sprint.domain.port.persistence.RegisterStagePort;
import edu.raijin.scrum.sprint.domain.port.persistence.UpdateStagePort;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.SprintsEntity;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.StagesEntity;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.mapper.StageEntityMapper;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository.JpaSprintRepository;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository.JpaStageRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class StageRepositoryAdapter implements RegisterStagePort, FindStagePort, UpdateStagePort {

    private final JpaStageRepository stageRepository;
    private final JpaSprintRepository sprintRepository;
    private final StageEntityMapper mapper;

    @Override
    public Optional<Stage> findBySprintIdAndId(Long sprintId, Long stageId) {
        return stageRepository.findByIdAndSprintIdAndDeletedFalse(stageId, sprintId).map(mapper::toDomain);
    }

    @Override
    public Stage update(Stage stage) {
        StagesEntity stageEntity = mapper.toEntity(stage);
        return mapper.toDomain(stageRepository.save(stageEntity));
    }

    @Override
    public Paged<Stage> findAll(Long sprintId, Pageable pageable) {
        Page<StagesEntity> stages = stageRepository.findBySprintIdAndDeletedFalse(sprintId, pageable);
        return Paged.from(stages.map(mapper::toDomain));
    }

    @Override
    public boolean existsSprint(Long sprintId) {
        return sprintRepository.existsByIdAndDeletedFalse(sprintId);
    }

    @Override
    public Stage create(Long sprintId, Stage stage) {
        SprintsEntity entity = sprintRepository.findById(sprintId).get();
        StagesEntity stageEntity = mapper.toEntity(stage).withSprint(entity);
        return mapper.toDomain(stageRepository.save(stageEntity));
    }

    @Override
    public boolean existsDefault(Long sprintId) {
        return stageRepository.existsBySprintIdAndIsDefaultTrue(sprintId);
    }

    @Override
    public boolean existsAnotherDefault(Long sprintId, Long stageId) {
        return stageRepository.existsByIdNotAndSprintIdAndIsDefaultTrue(stageId, sprintId);
    }
}
