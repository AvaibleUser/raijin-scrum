package edu.raijin.scrum.sprint.infrastructure.adapter.out.messaging.topic;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.SprintEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.shared.Audit;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.shared.infrastructure.config.property.KafkaTopicsProperty;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.port.messaging.CreatedSprintPublisherPort;
import edu.raijin.scrum.sprint.domain.port.messaging.DeletedSprintPublisherPort;
import edu.raijin.scrum.sprint.domain.port.messaging.UpdatedSprintPublisherPort;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.messaging.mapper.SprintEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SprintKafkaPublisherAdapter
        implements CreatedSprintPublisherPort, UpdatedSprintPublisherPort, DeletedSprintPublisherPort {

    private final SprintEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, SprintEvent> kafkaTemplate;

    private void publishSprint(String action, Sprint Sprint, Audit audit) {
        SprintEvent event = mapper.toEvent(Sprint, audit);
        kafkaTemplate.send(kafkaTopics.sprintCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedSprint(Sprint Sprint, UUID actorId) {
        Audit audit = Audit.builder()
                .eventType("sprint.created")
                .actorId(actorId)
                .description("Se ha creado el sprint")
                .build();

        publishSprint("create", Sprint, audit);
    }

    @Override
    public void publishUpdatedSprint(Sprint Sprint, UUID actorId) {
        Audit audit = Audit.builder()
                .eventType("sprint.updated")
                .actorId(actorId)
                .description("Se ha actualizado el sprint")
                .build();

        publishSprint("update", Sprint, audit);
    }

    @Override
    public void publishDeletedSprint(Sprint Sprint, UUID actorId) {
        Audit audit = Audit.builder()
                .eventType("sprint.deleted")
                .actorId(actorId)
                .description("Se ha eliminado el sprint")
                .build();

        publishSprint("delete", Sprint, audit);
    }
}
