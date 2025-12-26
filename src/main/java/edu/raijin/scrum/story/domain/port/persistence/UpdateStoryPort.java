package edu.raijin.scrum.story.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Story;

@Port
public interface UpdateStoryPort {

    Optional<Story> findByIdAndStageId(UUID storyId, Long stageId);

    Story update(Story story);
}