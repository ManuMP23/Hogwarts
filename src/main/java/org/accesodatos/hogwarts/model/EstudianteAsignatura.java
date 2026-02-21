package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Estudiante_Asignatura")
public class EstudianteAsignatura {

    @EmbeddedId
    private EstudianteAsignaturaId id;

    /*
     * Parte de la relación N:N (Estudiante <-> Asignatura) usando entidad intermedia.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAsignatura")
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @Column(name = "calificacion", precision = 3, scale = 1)
    private BigDecimal calificacion;
}
