package edu.raijin.scrum.story.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteStoryUseCase {

    void delete(Long stageId, Long storyId);
}