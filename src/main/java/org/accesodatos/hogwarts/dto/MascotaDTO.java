package org.accesodatos.hogwarts.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MascotaDTO {
    private Long id;
    private String nombre;
    private String especie;
    private String estudiante; // nombre completo del dueño
}
