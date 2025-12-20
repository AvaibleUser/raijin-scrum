package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface UpdateStoryStageUseCase {

    Story update(Long stageId, Long storyId, Long newStageId);

    Story update(UUID projectId, Long storyId, Long newStageId);
}
