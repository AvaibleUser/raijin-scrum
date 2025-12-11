package edu.raijin.scrum.project.domain.model;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.UUID;

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
        if (isNull(projectId)) {
            throw new IllegalArgumentException("El proyecto es requerido");
        }
        if (isNull(userId)) {
            throw new IllegalArgumentException("El usuario es requerido");
        }
        if (isNull(firstName)) {
            throw new IllegalArgumentException("El nombre es requerido");
        }
        if (isNull(lastName)) {
            throw new IllegalArgumentException("El apellido es requerido");
        }
        if (isNull(dpi)) {
            throw new IllegalArgumentException("El DPI es requerido");
        }
        if (isNull(email)) {
            throw new IllegalArgumentException("El email es requerido");
        }
    }

    public void update(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
