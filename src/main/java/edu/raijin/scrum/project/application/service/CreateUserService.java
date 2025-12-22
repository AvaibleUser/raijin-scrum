package edu.raijin.scrum.project.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.domain.port.persistence.RegisterUserPort;
import edu.raijin.scrum.project.domain.usecase.CreateUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final RegisterUserPort registerUser;

    @Override
    @Transactional
    public User create(User user) {
        if (registerUser.exists(user.getEmail())) {
            throw new RequestConflictException("El email ya se encuentra registrado");
        }
        user.checkValidRegistration();
        return registerUser.create(user);
    }
}
