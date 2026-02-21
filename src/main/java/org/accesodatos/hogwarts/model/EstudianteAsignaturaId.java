package org.accesodatos.hogwarts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Embeddable
public class EstudianteAsignaturaId implements Serializable {

    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @Column(name = "id_asignatura")
    private Long idAsignatura;
}
