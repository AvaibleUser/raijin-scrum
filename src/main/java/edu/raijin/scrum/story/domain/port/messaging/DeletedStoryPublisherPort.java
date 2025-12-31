package edu.raijin.scrum.story.domain.port.messaging;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Story;

@Port
public interface DeletedStoryPublisherPort {

    void publishDeletedStory(Story story, UUID actorId);
}
