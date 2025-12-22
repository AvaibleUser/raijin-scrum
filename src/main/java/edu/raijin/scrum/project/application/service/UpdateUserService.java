package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.domain.port.persistence.UpdateUserPort;
import edu.raijin.scrum.project.domain.usecase.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UpdateUserPort update;

    @Override
    @Transactional
    public User update(UUID userId, User user) {
        User toUpdate = update.findById(userId)
                .orElseThrow(() -> new ValueNotFoundException("El usuario no se encuentra registrado"));

        toUpdate.updateFrom(user);
        toUpdate.checkValidRegistration();
        return update.update(toUpdate);
    }
}
