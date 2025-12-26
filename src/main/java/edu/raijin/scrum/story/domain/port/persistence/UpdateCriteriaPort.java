package edu.raijin.scrum.story.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Criteria;

@Port
public interface UpdateCriteriaPort {

    Optional<Criteria> findByIdAndStoryId(Long criteriaId, UUID storyId);

    Criteria update(Criteria criteria);
}