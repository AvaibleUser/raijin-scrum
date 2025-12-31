package edu.raijin.scrum.project.domain.port.messaging;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.Project;

@Port
public interface CreatedProjectPublisherPort {

    void publishCreatedProject(Project project, UUID actorId);
}
