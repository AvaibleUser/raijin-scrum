package edu.raijin.scrum.project.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

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
public class User {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private String color;

    public void checkValidRegistration() {
        requireNonNull(id, () -> new BadRequestException("El usuario es requerido"));
        requireNonNull(firstName, () -> new BadRequestException("El nombre es requerido"));
        requireNonNull(lastName, () -> new BadRequestException("El apellido es requerido"));
        requireNonNull(email, () -> new BadRequestException("El email es requerido"));
        requireNonNull(role, () -> new BadRequestException("El rol es requerido"));
        requireNonNull(color, () -> new BadRequestException("El color es requerido"));
    }

    public void update(String firstName, String lastName, String role, String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.color = color;
    }
}
