package edu.raijin.scrum.project.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.UUID;

import edu.raijin.commons.util.exception.BadRequestException;
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
public class Member {

    private UUID projectId;

    private UUID userId;

    private String firstName;

    private String lastName;

    private String dpi;

    private String email;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonNull(projectId, () -> new BadRequestException("El proyecto es requerido"));
        requireNonNull(userId, () -> new BadRequestException("El usuario es requerido"));
        requireNonNull(firstName, () -> new BadRequestException("El nombre es requerido"));
        requireNonNull(lastName, () -> new BadRequestException("El apellido es requerido"));
        requireNonNull(dpi, () -> new BadRequestException("El DPI es requerido"));
        requireNonNull(email, () -> new BadRequestException("El email es requerido"));
    }

    public void update(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
