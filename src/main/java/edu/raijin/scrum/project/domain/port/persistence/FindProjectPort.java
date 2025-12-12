package edu.raijin.scrum.project.domain.port.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.Project;

@Port
public interface FindProjectPort {

    Optional<Project> findById(UUID id);

    List<Project> findAll();
}
