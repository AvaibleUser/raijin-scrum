package edu.raijin.scrum.story.domain.port.persistence;

import java.util.List;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Criteria;

@Port
public interface FindCriteriaPort {

    List<Criteria> findAll(Long storyId);
}