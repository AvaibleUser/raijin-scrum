package edu.raijin.scrum.story.domain.port.persistence;

import java.util.Optional;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Criteria;

@Port
public interface UpdateCriteriaPort {

    Optional<Criteria> findByIdAndStoryId(Long criteriaId, Long storyId);

    Criteria update(Criteria criteria);
}