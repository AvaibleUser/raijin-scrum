package edu.raijin.scrum.shared.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import edu.raijin.scrum.shared.infrastructure.config.property.KafkaTopicsProperty;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic projectTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.projectCommandsTopic()).build();
    }

    @Bean
    NewTopic sprintTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.sprintCommandsTopic()).build();
    }

    @Bean
    NewTopic storyTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.storyCommandsTopic()).build();
    }

    @Bean
    NewTopic memberTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.memberCommandsTopic()).build();
    }
}
