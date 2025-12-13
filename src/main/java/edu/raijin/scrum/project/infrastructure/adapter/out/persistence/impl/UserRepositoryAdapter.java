package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.impl;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.domain.port.persistence.RegisterUserPort;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements RegisterUserPort {

    private final JpaUserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User create(User user) {
        UsersEntity entity = repository.save(mapper.toEntity(user));

        return mapper.toDomain(entity);
    }
}
