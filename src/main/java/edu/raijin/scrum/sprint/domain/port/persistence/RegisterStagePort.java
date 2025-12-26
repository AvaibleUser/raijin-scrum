package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Stage;

@Port
public interface RegisterStagePort {

    boolean existsDefault(UUID sprintId);

    boolean existsSprint(UUID sprintId);

    Stage create(UUID sprintId, Stage stage);
}