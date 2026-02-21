package org.accesodatos.hogwarts.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class EstudianteDTO {
    private Long id;
    private String nombre; // "Nombre Apellido"
    private int anyoCurso;
    private LocalDate fechaNacimiento;
    private String casa;           // nombre de la casa
    private MascotaDTO mascota;    // info mascota
    private List<AsignaturaCalificacionDTO> asignaturas; // asignaturas + nota
}
