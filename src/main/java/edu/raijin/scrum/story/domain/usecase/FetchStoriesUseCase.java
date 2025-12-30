package edu.raijin.scrum.story.domain.usecase;

import java.util.List;
import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface FetchStoriesUseCase {

    List<Story> fetchAllBySprint(UUID sprintId);

    List<Story> fetchAll(UUID projectId);

    List<Story> fetchAll(Long stageId);
}