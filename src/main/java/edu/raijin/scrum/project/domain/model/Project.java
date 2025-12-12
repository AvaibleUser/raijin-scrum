package edu.raijin.scrum.project.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import edu.raijin.commons.domain.type.ProjectStatus;
import edu.raijin.commons.util.exception.BadRequestException;
import edu.raijin.commons.util.exception.RequestConflictException;
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
        requireNonNull(name, () -> new BadRequestException("El nombre del proyecto es requerido"));
        requireNonNull(status, () -> new BadRequestException("El estado del proyecto es requerido"));
        requireNonNull(monthlyIncome, () -> new BadRequestException("El ingreso mensual es requerido"));
        if (monthlyIncome.compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("El ingreso mensual es requerido");
        }
    }

    public void close() {
        if (this.status == ProjectStatus.CLOSED) {
            throw new RequestConflictException("El proyecto ya se encuentra cerrado");
        }
        this.status = ProjectStatus.CLOSED;
        this.closedAt = Instant.now();
    }

    public void updateFrom(Project updated) {
        if (this.status == ProjectStatus.CLOSED) {
            throw new RequestConflictException("El proyecto ya se encuentra cerrado");
        }
        this.name = firstNonNull(updated.name, this.name);
        this.description = firstNonNull(updated.description, this.description);
        this.client = firstNonNull(updated.client, this.client);
        this.monthlyIncome = firstNonNull(updated.monthlyIncome, this.monthlyIncome);
    }

    public void updateId(UUID id) {
        if (isNull(this.id)) {
            this.id = id;
        }
    }

    public void delete() {
        if (this.status == ProjectStatus.CLOSED) {
            throw new RequestConflictException("El proyecto ya se encuentra cerrado");
        }
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
