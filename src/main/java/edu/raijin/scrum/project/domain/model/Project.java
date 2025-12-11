package edu.raijin.scrum.project.domain.model;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import edu.raijin.commons.domain.type.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Data
@With
@Builder
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Project {

    private UUID id;

    private String name;

    private String description;

    private String client;

    @Builder.Default
    private ProjectStatus status = ProjectStatus.ACTIVE;

    @Builder.Default
    private BigDecimal monthlyIncome = BigDecimal.ZERO;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant closedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        if (isNull(name)) {
            throw new IllegalArgumentException("El nombre del proyecto es requerido");
        }
        if (isNull(status)) {
            throw new IllegalArgumentException("El estado del proyecto es requerido");
        }
        if (!isNull(monthlyIncome) && monthlyIncome.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El ingreso mensual es requerido");
        }
    }

    public void close() {
        if (this.status == ProjectStatus.CLOSED) {
            throw new IllegalStateException("El proyecto ya se encuentra cerrado");
        }
        this.status = ProjectStatus.CLOSED;
        this.closedAt = Instant.now();
    }

    public void update(String name, String description) {
        if (this.status == ProjectStatus.CLOSED) {
            throw new IllegalStateException("El proyecto ya se encuentra cerrado");
        }
        this.name = name;
        this.description = description;
    }

    public void updateId(UUID id) {
        if (isNull(this.id)) {
            this.id = id;
        }
    }
}
