package edu.raijin.scrum.project.infrastructure.adapter.in.messaging.topic;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.identity.UserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.domain.port.persistence.RegisterUserPort;
import edu.raijin.scrum.project.infrastructure.adapter.in.messaging.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserKafkaConsumerAdapter {

    private final RegisterUserPort registerUser;
    private final UserEventMapper mapper;

    @KafkaListener(topics = "${kafka.topics.user-commands.topic}", id = "create", properties = "${kafka.topics.user-commands.default-value}")
    public void consumeRegisteredUser(UserEvent event) {
        User user = mapper.toDomain(event);

        registerUser.create(user);
    }
}