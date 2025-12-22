package edu.raijin.scrum.project.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.ProjectEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.CreatedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.messaging.DeletedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.messaging.UpdatedProjectPublisherPort;
import edu.raijin.scrum.project.infrastructure.adapter.config.property.KafkaTopicsProperty;
import edu.raijin.scrum.project.infrastructure.adapter.out.messaging.mapper.ProjectEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectKafkaPublisherAdapter
        implements CreatedProjectPublisherPort, UpdatedProjectPublisherPort, DeletedProjectPublisherPort {

    private final ProjectEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, ProjectEvent> kafkaTemplate;

    private void publishProject(String action, Project project) {
        ProjectEvent event = mapper.toEvent(project);
        kafkaTemplate.send(kafkaTopics.projectCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedProject(Project project) {
        publishProject("create", project);
    }

    @Override
    public void publishUpdatedProject(Project project) {
        publishProject("update", project);
    }

    @Override
    public void publishDeletedProject(Project project) {
        publishProject("delete", project);
    }
}
