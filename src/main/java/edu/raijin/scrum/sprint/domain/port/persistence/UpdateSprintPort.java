package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@Port
public interface UpdateSprintPort {

    boolean existsAnotherActive(UUID projectId, UUID sprintId);

    Optional<Sprint> findByIdAndProjectId(UUID id, UUID projectId);

    Sprint update(Sprint sprint);
}