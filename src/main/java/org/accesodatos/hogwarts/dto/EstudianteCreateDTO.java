package org.accesodatos.hogwarts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class EstudianteCreateDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotNull
    @Min(1)
    @Max(7)
    private Integer anyoCurso;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    private Long casaId;

    @NotNull
    @Valid
    private MascotaCreateDTO mascota;
}
