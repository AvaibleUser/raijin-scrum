package edu.raijin.scrum.story.domain.port.persistence;

import java.util.List;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Story;

@Port
public interface FindStoryPort {

    List<Story> findAll(Long storyId);
}