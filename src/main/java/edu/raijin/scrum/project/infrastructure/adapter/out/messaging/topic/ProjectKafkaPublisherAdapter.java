package edu.raijin.scrum.project.infrastructure.adapter.out.messaging.topic;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.ProjectEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.shared.Audit;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.CreatedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.messaging.DeletedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.messaging.UpdatedProjectPublisherPort;
import edu.raijin.scrum.project.infrastructure.adapter.out.messaging.mapper.ProjectEventMapper;
import edu.raijin.scrum.shared.infrastructure.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectKafkaPublisherAdapter
        implements CreatedProjectPublisherPort, UpdatedProjectPublisherPort, DeletedProjectPublisherPort {

    private final ProjectEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, ProjectEvent> kafkaTemplate;

    private void publishProject(String action, Project project, Audit audit) {
        ProjectEvent event = mapper.toEvent(project, audit);
        kafkaTemplate.send(kafkaTopics.projectCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedProject(Project project, UUID actorId) {
        Audit audit = Audit.builder()
                .eventType("project.created")
                .actorId(actorId)
                .description("Se ha creado el proyecto")
                .build();

        publishProject("create", project, audit);
    }

    @Override
    public void publishUpdatedProject(Project project, UUID actorId, boolean closed) {
        Audit audit = Audit.builder()
                .eventType(closed ? "project.closed" : "project.updated")
                .actorId(actorId)
                .description("Se ha actualizado el proyecto")
                .build();

        publishProject("update", project, audit);
    }

    @Override
    public void publishDeletedProject(Project project, UUID actorId) {
        Audit audit = Audit.builder()
                .eventType("project.deleted")
                .actorId(actorId)
                .description("Se ha eliminado el proyecto")
                .build();

        publishProject("delete", project, audit);
    }
}
