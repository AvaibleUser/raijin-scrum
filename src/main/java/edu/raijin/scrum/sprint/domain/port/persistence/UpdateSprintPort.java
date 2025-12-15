package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@Port
public interface UpdateSprintPort {

    boolean existsActiveSprint(UUID projectId);

    Optional<Sprint> findByIdAndProjectId(Long id, UUID projectId);

    Sprint update(Sprint sprint);
}