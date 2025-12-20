package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface CreateStoryUseCase {

    Story create(Long stageId, Story story);

    Story create(UUID projectId, Story story);
}