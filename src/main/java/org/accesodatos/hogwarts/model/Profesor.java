package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long idProfesor;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    /*
     * CAMBIO (según eldiagrama): Profesor <-> Asignatura es 1:1.
     * Antes: ManyToOne (muchos profesores podrían compartir una asignatura).
     * Ahora: Un profesor imparte una única asignatura y una asignatura la imparte un único profesor.
     *
     * JoinColumn indica que la FK está en esta tabla (profesor.id_asignatura).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    /*
     * CAMBIO (según diagrama): Casa <-> Profesor es 1:1 (jefe de casa).
     * En tu BD, la FK id_jefe está en CASA, así que la relación "dueña" vive en Casa.
     * Aquí definimos el lado inverso con mappedBy="jefe".
     */
    @JsonIgnore
    @OneToOne(mappedBy = "jefe", fetch = FetchType.LAZY)
    private Casa casaJefe;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
}
