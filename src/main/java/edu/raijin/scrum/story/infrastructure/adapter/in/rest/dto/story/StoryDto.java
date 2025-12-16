package edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story;

import java.util.UUID;

import edu.raijin.commons.domain.type.StoryPriority;

public record StoryDto(
        Long id,
        UUID productOwnerId,
        UUID developerId,
        String name,
        String description,
        Integer points,
        StoryPriority priority) {
}
