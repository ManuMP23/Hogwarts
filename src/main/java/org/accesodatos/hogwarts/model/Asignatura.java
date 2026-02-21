package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private Long idAsignatura;

    @Column(name = "nombre_asignatura", nullable = false, length = 100)
    private String nombreAsignatura;

    @Column(name = "aula", length = 50)
    private String aula;

    @Column(name = "obligatoria")
    private Boolean obligatoria;


    @JsonIgnore
    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY)
    private List<Profesor> profesores;

    /*
     * Relación N:N con Estudiante mediante la entidad intermedia EstudianteAsignatura.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY)
    private List<EstudianteAsignatura> matriculas;
}
