package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@Port
public interface FindSprintPort {

    Paged<Sprint> findAll(UUID projectId, Pageable pageable);
}