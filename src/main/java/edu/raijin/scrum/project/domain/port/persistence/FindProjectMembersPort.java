package edu.raijin.scrum.project.domain.port.persistence;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.User;

@Port
public interface FindProjectMembersPort {

    boolean existsProject(UUID projectId);

    Paged<User> findAll(UUID projectId, Pageable pageable);
}
