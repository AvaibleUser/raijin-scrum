package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface UpdateStoryUseCase {

    Story update(Long stageId, UUID storyId, Story story, UUID actorId);

    Story update(UUID projectId, UUID storyId, Story story, UUID actorId);
}