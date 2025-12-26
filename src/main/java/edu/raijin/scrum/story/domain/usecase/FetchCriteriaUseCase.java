package edu.raijin.scrum.story.domain.usecase;

import java.util.List;
import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Criteria;

@UseCase
public interface FetchCriteriaUseCase {

    List<Criteria> fetchAll(UUID storyId);
}