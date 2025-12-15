package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.raijin.commons.domain.type.SprintStatus;

public record UpdateSprintDto(
        String name,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        SprintStatus status) {
}
