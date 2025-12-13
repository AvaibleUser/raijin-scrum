package edu.raijin.scrum.project.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.User;

@Port
public interface RegisterUserPort {

    boolean exists(String email);

    User create(User user);
}
