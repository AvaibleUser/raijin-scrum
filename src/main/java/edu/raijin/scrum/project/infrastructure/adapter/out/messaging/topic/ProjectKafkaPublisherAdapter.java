package edu.raijin.scrum.project.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.ProjectEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.CreatedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.messaging.UpdatedProjectPublisherPort;
import edu.raijin.scrum.project.infrastructure.adapter.config.property.KafkaTopicsProperty;
import edu.raijin.scrum.project.infrastructure.adapter.out.messaging.mapper.ProjectEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectKafkaPublisherAdapter implements CreatedProjectPublisherPort, UpdatedProjectPublisherPort {

    private final ProjectEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, ProjectEvent> kafkaTemplate;

    @Override
    public void publishCreatedProject(Project project) {
        ProjectEvent event = mapper.toEvent(project);
        kafkaTemplate.send(kafkaTopics.projectCommandsTopic(), "create", event);
    }

    @Override
    public void publishUpdatedProject(Project project) {
        ProjectEvent event = mapper.toEvent(project);
        kafkaTemplate.send(kafkaTopics.projectCommandsTopic(), "update", event);
    }
}
