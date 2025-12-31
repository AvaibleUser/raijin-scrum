package edu.raijin.scrum.project.domain.port.messaging;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.Project;

@Port
public interface UpdatedProjectPublisherPort {

    void publishUpdatedProject(Project project, UUID actorId, boolean closed);
}
