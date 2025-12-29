package edu.raijin.scrum.project.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.MemberEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Member;
import edu.raijin.scrum.project.domain.port.messaging.CreatedMemberPublisherPort;
import edu.raijin.scrum.project.domain.port.messaging.DeletedMemberPublisherPort;
import edu.raijin.scrum.project.infrastructure.adapter.out.messaging.mapper.MemberEventMapper;
import edu.raijin.scrum.shared.infrastructure.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class MemberKafkaPublisherAdapter implements CreatedMemberPublisherPort, DeletedMemberPublisherPort {

    private final MemberEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, MemberEvent> kafkaTemplate;

    private void publishMember(String action, Member member) {
        MemberEvent event = mapper.toEvent(member);
        kafkaTemplate.send(kafkaTopics.memberCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedMember(Member member) {
        publishMember("create", member);
    }

    @Override
    public void publishDeletedMember(Member member) {
        publishMember("delete", member);
    }
}
