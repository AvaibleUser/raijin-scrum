package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.domain.port.persistence.UpdateUserPort;
import edu.raijin.scrum.project.domain.usecase.DeleteUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {

    private final UpdateUserPort update;

    @Override
    @Transactional
    public void delete(UUID userId) {
        User user = update.findById(userId).orElse(null);
        if (user == null) {
            return;
        }

        user.delete();
        update.update(user);
    }
}
