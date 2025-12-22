package edu.raijin.scrum.project.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.User;

@Port
public interface UpdateUserPort {

    Optional<User> findById(UUID id);

    User update(User user);
}
