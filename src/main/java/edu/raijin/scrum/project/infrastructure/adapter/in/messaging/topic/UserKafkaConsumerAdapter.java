package edu.raijin.scrum.project.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.identity.UserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.domain.usecase.CreateUserUseCase;
import edu.raijin.scrum.project.domain.usecase.DeleteUserUseCase;
import edu.raijin.scrum.project.domain.usecase.UpdateUserUseCase;
import edu.raijin.scrum.project.infrastructure.adapter.in.messaging.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserKafkaConsumerAdapter {

    private final CreateUserUseCase create;
    private final UpdateUserUseCase update;
    private final DeleteUserUseCase delete;
    private final UserEventMapper mapper;

    private void consumeCreatedUser(User user) {
        create.create(user);
    }

    private void consumeUpdatedUser(User user) {
        update.update(user.getId(), user);
    }

    private void consumeDeletedUser(User user) {
        delete.delete(user.getId());
    }

    @KafkaListener(topics = "${kafka.topics.user-commands.topic}", properties = "${kafka.topics.user-commands.properties}", groupId = "scrum-service")
    public void consumeUserEvent(@Payload UserEvent event, @Header(RECEIVED_KEY) String key) {
        User user = mapper.toDomain(event);
        switch (key) {
            case "create" -> consumeCreatedUser(user);
            case "update" -> consumeUpdatedUser(user);
            case "delete" -> consumeDeletedUser(user);
        }
    }
}