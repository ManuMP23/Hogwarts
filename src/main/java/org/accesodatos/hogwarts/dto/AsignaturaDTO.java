package org.accesodatos.hogwarts.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AsignaturaDTO {
    private Long id;
    private String nombre;
    private String aula;
    private Boolean obligatoria;
    private String profesor; // nombre completo del profesor encargado
}
