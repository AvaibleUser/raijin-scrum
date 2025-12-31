package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface UpdateStoryStageUseCase {

    Story update(Long stageId, UUID storyId, Long newStageId, UUID actorId);

    Story update(UUID projectId, UUID storyId, Long newStageId, UUID actorId);

    Story update(UUID sprintId, UUID storyId, UUID actorId);
}
