package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteCriteriaUseCase {

    void delete(UUID storyId, Long criteriaId);
}