package edu.raijin.scrum.story.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.StoryEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.shared.infrastructure.config.property.KafkaTopicsProperty;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.messaging.CreatedStoryPublisherPort;
import edu.raijin.scrum.story.domain.port.messaging.DeletedStoryPublisherPort;
import edu.raijin.scrum.story.domain.port.messaging.UpdatedStoryPublisherPort;
import edu.raijin.scrum.story.infrastructure.adapter.out.messaging.mapper.StoryEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class StoryKafkaPublisherAdapter
        implements CreatedStoryPublisherPort, UpdatedStoryPublisherPort, DeletedStoryPublisherPort {

    private final StoryEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, StoryEvent> kafkaTemplate;

    private void publishStory(String action, Story Story) {
        StoryEvent event = mapper.toEvent(Story);
        kafkaTemplate.send(kafkaTopics.storyCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedStory(Story Story) {
        publishStory("create", Story);
    }

    @Override
    public void publishUpdatedStory(Story Story) {
        publishStory("update", Story);
    }

    @Override
    public void publishDeletedStory(Story Story) {
        publishStory("delete", Story);
    }
}
