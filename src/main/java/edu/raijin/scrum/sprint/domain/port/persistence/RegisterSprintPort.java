package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@Port
public interface RegisterSprintPort {

    boolean existsProject(UUID id);

    Sprint create(UUID projectId, Sprint sprint);
}
