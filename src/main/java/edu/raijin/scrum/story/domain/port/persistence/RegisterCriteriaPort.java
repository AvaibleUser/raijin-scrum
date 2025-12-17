package edu.raijin.scrum.story.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Criteria;

@Port
public interface RegisterCriteriaPort {

    boolean existsStory(Long storyId);

    Criteria create(Long storyId, Criteria criteria);
}