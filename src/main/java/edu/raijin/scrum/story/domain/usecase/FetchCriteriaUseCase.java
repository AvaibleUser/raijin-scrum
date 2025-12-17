package edu.raijin.scrum.story.domain.usecase;

import java.util.List;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Criteria;

@UseCase
public interface FetchCriteriaUseCase {

    List<Criteria> fetchAll(Long storyId);
}