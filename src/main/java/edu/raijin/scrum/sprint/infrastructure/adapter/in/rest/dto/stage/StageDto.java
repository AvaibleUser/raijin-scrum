package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.stage;

import java.time.Instant;

public record StageDto(
        Long id,
        String name,
        String description,
        Integer orderIndex,
        Boolean isDefault,
        Instant createdAt,
        Instant updatedAt) {
}
