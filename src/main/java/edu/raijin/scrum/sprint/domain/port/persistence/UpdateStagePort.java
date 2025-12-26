package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Stage;

@Port
public interface UpdateStagePort {

    boolean existsAnotherDefault(UUID sprintId, Long stageId);

    Optional<Stage> findBySprintIdAndId(UUID sprintId, Long stageId);

    Stage update(Stage stage);
}