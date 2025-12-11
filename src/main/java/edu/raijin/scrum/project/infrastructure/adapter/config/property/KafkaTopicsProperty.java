package edu.raijin.scrum.project.infrastructure.adapter.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka.topics")
public record KafkaTopicsProperty(
        String projectCommandsTopic) {
}
