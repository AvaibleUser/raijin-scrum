package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.SprintStatus;

public record SprintDto(
        UUID id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        SprintStatus status,
        Instant createdAt,
        Instant updatedAt) {
}
