package edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddProjectDto(
        @NotBlank(message = "El nombre del proyecto es requerido") String name,
        String description,
        String client,
        @Min(value = 0, message = "El ingreso mensual debe ser mayor o igual a cero") BigDecimal monthlyIncome) {
}
