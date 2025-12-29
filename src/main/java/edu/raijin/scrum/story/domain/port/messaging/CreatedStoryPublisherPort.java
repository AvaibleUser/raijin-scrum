package edu.raijin.scrum.story.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.story.domain.model.Story;

@Port
public interface CreatedStoryPublisherPort {

    void publishCreatedStory(Story story);
}
