package edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.criteria;

import java.time.Instant;

public record CriteriaDto(
        Long id,
        String description,
        Boolean reached,
        Boolean required,
        Instant createdAt,
        Instant updatedAt) {
}
