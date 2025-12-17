package edu.raijin.scrum.story.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteCriteriaUseCase {

    void delete(Long storyId, Long criteriaId);
}