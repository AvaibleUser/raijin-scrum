package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.port.persistence.FindSprintPort;
import edu.raijin.scrum.sprint.domain.port.persistence.RegisterSprintPort;
import edu.raijin.scrum.sprint.domain.port.persistence.UpdateSprintPort;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.SprintsEntity;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.mapper.SprintEntityMapper;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository.JpaSprintRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SprintRepositoryAdapter implements RegisterSprintPort, FindSprintPort, UpdateSprintPort {

    private final JpaSprintRepository sprintRepository;
    private final JpaProjectRepository projectRepository;
    private final SprintEntityMapper mapper;

    @Override
    public boolean existsProject(UUID id) {
        return projectRepository.existsByIdAndDeletedFalse(id);
    }

    @Override
    public Sprint create(UUID projectId, Sprint sprint) {
        ProjectsEntity project = projectRepository.findById(projectId).get();
        SprintsEntity entity = mapper.toEntity(sprint).withProject(project);

        return mapper.toSprint(sprintRepository.save(entity));
    }

    @Override
    public Paged<Sprint> findAll(UUID projectId, Pageable pageable) {
        Page<SprintsEntity> sprints = sprintRepository.findByProjectIdAndDeletedFalse(projectId, pageable);
        return Paged.from(sprints.map(mapper::toSprint));
    }

    @Override
    public Optional<Sprint> findByIdAndProjectId(Long id, UUID projectId) {
        return sprintRepository.findByIdAndProjectId(id, projectId)
                .map(mapper::toSprint);
    }

    @Override
    public Sprint update(Sprint sprint) {
        SprintsEntity entity = mapper.toEntity(sprint);
        return mapper.toSprint(sprintRepository.save(entity));
    }

    @Override
    public boolean existsActiveSprint(UUID projectId) {
        return sprintRepository.existsByActiveAndProjectId(projectId);
    }

    @Override
    public boolean existsAnotherActive(UUID projectId, Long sprintId) {
        return sprintRepository.existsByActiveAndNotIdAndProjectId(sprintId, projectId);
    }
}
