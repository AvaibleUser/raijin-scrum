package edu.raijin.scrum.sprint.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Stage;

@Port
public interface FindStagePort {

    Paged<Stage> findAll(Long sprintId, Pageable pageable);
}