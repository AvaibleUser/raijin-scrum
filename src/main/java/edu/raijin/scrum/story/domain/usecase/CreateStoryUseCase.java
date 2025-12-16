package edu.raijin.scrum.story.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface CreateStoryUseCase {

    Story create(Long stageId, Story story);
}