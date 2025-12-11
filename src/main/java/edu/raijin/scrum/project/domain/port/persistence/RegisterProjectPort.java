package edu.raijin.scrum.project.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.Project;

@Port
public interface RegisterProjectPort {

    UUID create(Project project);
}
