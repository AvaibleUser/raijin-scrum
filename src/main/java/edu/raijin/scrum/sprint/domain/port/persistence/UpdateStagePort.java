package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.Optional;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Stage;

@Port
public interface UpdateStagePort {

    boolean existsAnotherDefault(Long sprintId, Long stageId);

    Optional<Stage> findBySprintIdAndId(Long sprintId, Long stageId);

    Stage update(Stage stage);
}