package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Criteria;

@UseCase
public interface CreateCriteriaUseCase {

    Criteria create(UUID storyId, Criteria criteria);
}