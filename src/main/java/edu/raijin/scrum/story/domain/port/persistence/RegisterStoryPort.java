package edu.raijin.scrum.story.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Story;

@Port
public interface RegisterStoryPort {

    boolean existsUser(UUID id);

    boolean existsStage(Long stageId);

    boolean existsProject(UUID projectId);

    Story create(Long stageId, Story story);

    Story create(UUID projectId, Story story);
}