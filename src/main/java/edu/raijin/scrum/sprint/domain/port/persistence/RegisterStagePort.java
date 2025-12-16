package edu.raijin.scrum.sprint.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Stage;

@Port
public interface RegisterStagePort {

    boolean existsDefault(Long sprintId);

    boolean existsSprint(Long sprintId);

    Stage create(Long sprintId, Stage stage);
}