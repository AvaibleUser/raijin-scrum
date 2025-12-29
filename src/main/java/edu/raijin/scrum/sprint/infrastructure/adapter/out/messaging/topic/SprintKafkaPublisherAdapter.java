package edu.raijin.scrum.sprint.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.SprintEvent;
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

    private void publishSprint(String action, Sprint Sprint) {
        SprintEvent event = mapper.toEvent(Sprint);
        kafkaTemplate.send(kafkaTopics.sprintCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedSprint(Sprint Sprint) {
        publishSprint("create", Sprint);
    }

    @Override
    public void publishUpdatedSprint(Sprint Sprint) {
        publishSprint("update", Sprint);
    }

    @Override
    public void publishDeletedSprint(Sprint Sprint) {
        publishSprint("delete", Sprint);
    }
}
