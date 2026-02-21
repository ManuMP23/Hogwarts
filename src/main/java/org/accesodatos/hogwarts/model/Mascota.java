package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;

    @Column(name = "nombre_mascota", nullable = false, length = 50)
    private String nombreMascota;

    @Column(name = "especie", nullable = false, length = 50)
    private String especie;

    /*
     * CAMBIO (según el diagrama): Mascota <-> Estudiante es 1:1.
     * Antes: ManyToOne (varias mascotas podían apuntar al mismo estudiante).
     * Ahora: una mascota pertenece a un único estudiante y viceversa.

     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;
}
