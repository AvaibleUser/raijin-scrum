package edu.raijin.scrum.story.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Criteria;

@UseCase
public interface CreateCriteriaUseCase {

    Criteria create(Long storyId, Criteria criteria);
}