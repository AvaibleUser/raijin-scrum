package edu.raijin.scrum.story.domain.port.persistence;

import java.util.List;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Story;

@Port
public interface FindStoryPort {

    List<Story> findAll(UUID projectId);

    List<Story> findAll(Long stageId);
}