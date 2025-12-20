package edu.raijin.scrum.story.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteStoryUseCase {

    void delete(Long stageId, Long storyId);

    void delete(UUID projectId, Long storyId);
}