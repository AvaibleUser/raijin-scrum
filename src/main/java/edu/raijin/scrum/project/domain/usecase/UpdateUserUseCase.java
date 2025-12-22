package edu.raijin.scrum.project.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.project.domain.model.User;

@UseCase
public interface UpdateUserUseCase {

    User update(UUID userId, User user);
}
