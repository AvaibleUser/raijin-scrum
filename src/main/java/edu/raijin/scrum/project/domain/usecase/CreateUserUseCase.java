package edu.raijin.scrum.project.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.project.domain.model.User;

@UseCase
public interface CreateUserUseCase {

    User create(User member);
}
