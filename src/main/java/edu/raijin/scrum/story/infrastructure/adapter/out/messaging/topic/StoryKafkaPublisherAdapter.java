package edu.raijin.scrum.story.infrastructure.adapter.out.messaging.topic;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.StoryEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.shared.Audit;
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

    private void publishStory(String action, Story Story, Audit audit) {
        StoryEvent event = mapper.toEvent(Story, audit);
        kafkaTemplate.send(kafkaTopics.storyCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedStory(Story Story, UUID actorId) {
        Audit audit = Audit.builder()
                .eventType("story.created")
                .actorId(actorId)
                .description("Se ha creado la historia")
                .build();

        publishStory("create", Story, audit);
    }

    @Override
    public void publishUpdatedStory(Story Story, UUID actorId, boolean moved) {
        Audit audit = Audit.builder()
                .eventType(moved ? "story.moved" : "story.updated")
                .actorId(actorId)
                .description("Se ha actualizado la historia")
                .build();

        publishStory("update", Story, audit);
    }

    @Override
    public void publishDeletedStory(Story Story, UUID actorId) {
        Audit audit = Audit.builder()
                .eventType("story.deleted")
                .actorId(actorId)
                .description("Se ha eliminado la historia")
                .build();

        publishStory("delete", Story, audit);
    }
}
