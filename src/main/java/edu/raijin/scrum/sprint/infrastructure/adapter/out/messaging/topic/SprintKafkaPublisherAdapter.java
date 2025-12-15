package edu.raijin.scrum.sprint.infrastructure.adapter.out.messaging.topic;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.port.messaging.CreatedSprintPublisherPort;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SprintKafkaPublisherAdapter implements CreatedSprintPublisherPort {

    @Override
    public void publishCreatedSprint(Sprint sprint) {
    }
}
