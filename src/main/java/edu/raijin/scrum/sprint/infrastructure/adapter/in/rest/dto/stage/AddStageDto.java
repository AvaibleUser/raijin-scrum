package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.stage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddStageDto(
        @NotBlank(message = "El nombre de la columna es requerido") String name,
        String description,
        @NotNull(message = "El orden de la columna es requerido") Integer orderIndex,
        Boolean isDefault) {
}
