package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Stage;

@Port
public interface FindStagePort {

    Optional<Stage> findDefaultBySprint(UUID sprintId);

    List<Stage> findAll(UUID sprintId);
}