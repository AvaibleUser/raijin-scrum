package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Member;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.persistence.AddProjectMemberPort;
import edu.raijin.scrum.project.domain.port.persistence.CloseProjectPort;
import edu.raijin.scrum.project.domain.port.persistence.FindProjectPort;
import edu.raijin.scrum.project.domain.port.persistence.RegisterProjectPort;
import edu.raijin.scrum.project.domain.port.persistence.RemoveProjectMemberPort;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.mapper.ProjectEntityMapper;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository.JpaSprintRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements FindProjectPort, RegisterProjectPort, CloseProjectPort,
        AddProjectMemberPort, RemoveProjectMemberPort {

    private final JpaProjectRepository projectRepository;
    private final JpaSprintRepository sprintRepository;
    private final JpaUserRepository userRepository;
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
    public Paged<Project> findAllAssigned(UUID userId, Pageable pageable) {
        Page<Project> projects = projectRepository.findAllByMembersIdAndDeletedFalse(userId, pageable)
                .map(mapper::toDomain);
        return Paged.from(projects);
    }

    @Override
    public Project update(Project project) {
        ProjectsEntity entity = mapper.toEntity(project);
        return mapper.toDomain(projectRepository.save(entity));
    }

    @Override
    public boolean hasActiveSprintsAttached(UUID id) {
        return sprintRepository.existsByNotFinishedAndProjectId(id);
    }

    @Override
    public boolean exists(UUID projectId, UUID userId) {
        return projectRepository.existsByIdAndMembersIdAndDeletedFalse(projectId, userId);
    }

    @Override
    public void add(Member member) {
        ProjectsEntity project = projectRepository.findById(member.getProjectId()).get();
        UsersEntity user = userRepository.findById(member.getUserId()).get();

        Hibernate.initialize(project.getMembers());
        project.getMembers().add(user);

        projectRepository.save(project);
    }

    @Override
    public void remove(Member member) {
        ProjectsEntity project = projectRepository.findById(member.getProjectId()).get();
        UsersEntity user = userRepository.findById(member.getUserId()).get();

        Hibernate.initialize(project.getMembers());
        project.getMembers().remove(user);

        projectRepository.save(project);
    }
}
