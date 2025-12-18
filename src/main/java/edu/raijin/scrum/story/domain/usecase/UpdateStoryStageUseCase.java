package edu.raijin.scrum.story.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface UpdateStoryStageUseCase {

    Story update(Long stageId, Long storyId, Long newStageId);
}
