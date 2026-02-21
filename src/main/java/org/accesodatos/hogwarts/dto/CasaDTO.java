package org.accesodatos.hogwarts.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CasaDTO {
    private Long id;
    private String nombre;
    private String fundador;
    private String fantasma;
    private ProfesorDTO jefe;
    private List<String> estudiantes; // lista de nombres completos
}
