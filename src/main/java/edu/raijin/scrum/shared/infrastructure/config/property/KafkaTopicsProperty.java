package edu.raijin.scrum.shared.infrastructure.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka.topics")
public record KafkaTopicsProperty(
        String projectCommandsTopic,
        String sprintCommandsTopic,
        String storyCommandsTopic,
        String memberCommandsTopic) {
}
