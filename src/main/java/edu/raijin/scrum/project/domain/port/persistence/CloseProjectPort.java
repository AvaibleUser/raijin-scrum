package edu.raijin.scrum.project.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface CloseProjectPort extends UpdateProjectPort {

    boolean hasSprintsAttached(UUID id);
}
