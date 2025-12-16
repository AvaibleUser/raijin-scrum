package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.domain.port.persistence.FindProjectMembersPort;
import edu.raijin.scrum.project.domain.port.persistence.RegisterUserPort;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements RegisterUserPort, FindProjectMembersPort {

    private final JpaUserRepository userRepository;
    private final JpaProjectRepository projectRepository;
    private final UserEntityMapper mapper;

    @Override
    public boolean exists(String email) {
        return userRepository.existsByEmailAndDeletedFalse(email);
    }

    @Override
    public User create(User user) {
        UsersEntity entity = userRepository.save(mapper.toEntity(user));

        return mapper.toDomain(entity);
    }

    @Override
    public boolean existsProject(UUID projectId) {
        return projectRepository.existsByIdAndDeletedFalse(projectId);
    }

    @Override
    public Paged<User> findAll(UUID projectId, Pageable pageable) {
        Page<UsersEntity> users = userRepository.findByDeletedFalse(pageable);
        return Paged.from(users.map(mapper::toDomain));
    }
}
