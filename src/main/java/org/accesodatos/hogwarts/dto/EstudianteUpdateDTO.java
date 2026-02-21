package org.accesodatos.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class EstudianteUpdateDTO {

    @NotNull
    @Min(1)
    private Integer anyoCurso;

    @NotNull
    private LocalDate fechaNacimiento;

    /**
     * Puede ser null para borrar la mascota
     */
    @Valid
    private MascotaCreateDTO mascota;
}
