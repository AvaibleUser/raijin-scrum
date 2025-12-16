package edu.raijin.scrum.sprint.domain.port.persistence;

import java.util.List;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.sprint.domain.model.Stage;

@Port
public interface FindStagePort {

    List<Stage> findAll(Long sprintId);
}