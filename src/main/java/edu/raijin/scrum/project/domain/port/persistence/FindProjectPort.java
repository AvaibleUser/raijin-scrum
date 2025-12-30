package edu.raijin.scrum.project.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.Project;

@Port
public interface FindProjectPort {

    Optional<Project> findById(UUID id);

    Paged<Project> findAllAssigned(UUID userId, Pageable pageable);

    Paged<Project> findAll(Pageable pageable);
}
