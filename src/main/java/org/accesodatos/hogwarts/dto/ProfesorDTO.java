package org.accesodatos.hogwarts.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProfesorDTO {
    private Long id;
    private String nombre;         // "Nombre Apellido"
    private String asignatura;      // nombre de la asignatura
    private LocalDate fechaInicio;
}
