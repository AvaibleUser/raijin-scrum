package edu.raijin.scrum.story.domain.usecase;

import java.util.List;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.story.domain.model.Story;

@UseCase
public interface FetchStoriesUseCase {

    List<Story> fetchAll(Long stageId);
}