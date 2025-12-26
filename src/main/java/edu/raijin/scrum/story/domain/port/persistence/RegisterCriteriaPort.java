package edu.raijin.scrum.story.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Criteria;

@Port
public interface RegisterCriteriaPort {

    boolean existsStory(UUID storyId);

    Criteria create(UUID storyId, Criteria criteria);
}