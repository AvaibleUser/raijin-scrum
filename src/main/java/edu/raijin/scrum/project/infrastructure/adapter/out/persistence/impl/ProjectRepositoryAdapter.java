package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.persistence.CloseProjectPort;
import edu.raijin.scrum.project.domain.port.persistence.FindProjectPort;
import edu.raijin.scrum.project.domain.port.persistence.RegisterProjectPort;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.mapper.ProjectEntityMapper;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements FindProjectPort, RegisterProjectPort, CloseProjectPort {

    private final JpaProjectRepository projectRepository;
    private final ProjectEntityMapper mapper;

    @Override
    public UUID create(Project project) {
        ProjectsEntity entity = mapper.toEntity(project);
        return projectRepository.save(entity).getId();
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return projectRepository.findByIdAndDeletedFalse(id)
                .map(mapper::toDomain);
    }

    @Override
    public Paged<Project> findAll(Pageable pageable) {
        Page<Project> projects = projectRepository.findAllByDeletedFalse(pageable).map(mapper::toDomain);
        return Paged.from(projects);
    }

    @Override
    public Project update(Project project) {
        ProjectsEntity entity = mapper.toEntity(project);
        return mapper.toDomain(projectRepository.save(entity));
    }

    @Override
    public boolean hasSprintsAttached(UUID id) {
        // TODO: implement project sprints check
        return projectRepository.existsById(id);
    }
}
