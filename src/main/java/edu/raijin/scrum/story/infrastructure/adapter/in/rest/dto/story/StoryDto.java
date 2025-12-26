package edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story;

import java.util.UUID;

import edu.raijin.commons.domain.type.StoryPriority;

public record StoryDto(
        UUID id,
        Long stageId,
        UUID projectId,
        UUID productOwnerId,
        UUID developerId,
        String name,
        String description,
        Integer points,
        StoryPriority priority) {
}
