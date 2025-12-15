package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddSprintDto(
        @NotBlank(message = "El nombre del sprint es requerido") String name,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha de inicio del sprint es requerida") LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha de fin del sprint es requerida") LocalDate endDate) {
}
