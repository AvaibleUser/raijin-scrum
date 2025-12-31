package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteStoryUseCase {

    void delete(Long stageId, UUID storyId, UUID actorId);

    void delete(UUID projectId, UUID storyId, UUID actorId);
}