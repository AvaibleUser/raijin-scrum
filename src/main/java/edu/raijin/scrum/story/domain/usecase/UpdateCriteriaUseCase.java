package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Criteria;

@UseCase
public interface UpdateCriteriaUseCase {

    Criteria update(UUID storyId, Long criteriaId, Criteria criteria);
}