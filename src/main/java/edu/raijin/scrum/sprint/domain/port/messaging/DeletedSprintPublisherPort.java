package edu.raijin.scrum.sprint.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@Port
public interface DeletedSprintPublisherPort {

    void publishDeletedSprint(Sprint sprint);
}
