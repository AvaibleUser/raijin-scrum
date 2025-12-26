package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

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
    public Optional<Stage> findBySprintIdAndId(UUID sprintId, Long stageId) {
        return stageRepository.findByIdAndSprintIdAndDeletedFalse(stageId, sprintId).map(mapper::toDomain);
    }

    @Override
    public Stage update(Stage stage) {
        StagesEntity stageEntity = mapper.toEntity(stage);
        return mapper.toDomain(stageRepository.save(stageEntity));
    }

    @Override
    public List<Stage> findAll(UUID sprintId) {
        List<StagesEntity> stages = stageRepository.findBySprintIdAndDeletedFalse(sprintId);
        return stages.stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsSprint(UUID sprintId) {
        return sprintRepository.existsByIdAndDeletedFalse(sprintId);
    }

    @Override
    public Stage create(UUID sprintId, Stage stage) {
        SprintsEntity entity = sprintRepository.findById(sprintId).get();
        StagesEntity stageEntity = mapper.toEntity(stage).withSprint(entity);
        return mapper.toDomain(stageRepository.save(stageEntity));
    }

    @Override
    public boolean existsDefault(UUID sprintId) {
        return stageRepository.existsBySprintIdAndIsDefaultTrue(sprintId);
    }

    @Override
    public boolean existsAnotherDefault(UUID sprintId, Long stageId) {
        return stageRepository.existsByIdNotAndSprintIdAndIsDefaultTrue(stageId, sprintId);
    }
}
