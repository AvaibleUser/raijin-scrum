package edu.raijin.scrum.story.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Criteria;

@UseCase
public interface UpdateCriteriaUseCase {

    Criteria update(Long storyId, Long criteriaId, Criteria criteria);
}