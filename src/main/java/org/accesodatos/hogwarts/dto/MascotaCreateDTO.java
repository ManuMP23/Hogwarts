package org.accesodatos.hogwarts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MascotaCreateDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String especie;
}
