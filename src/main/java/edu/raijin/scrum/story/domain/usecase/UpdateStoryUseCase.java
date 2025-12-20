package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface UpdateStoryUseCase {

    Story update(Long stageId, Long storyId, Story story);

    Story update(UUID projectId, Long storyId, Story story);
}