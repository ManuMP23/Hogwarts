package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Casa")
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_casa")
    private Long idCasa;

    @Column(name = "nombre_casa", nullable = false, length = 50)
    private String nombreCasa;

    @Column(name = "fundador", nullable = false, length = 50)
    private String fundador;
    @Column(name = "id_jefe", nullable = false)
    private Integer idJefe;

    @Column(name = "fantasma", nullable = false, length = 50)
    private String fantasma;

    @JsonIgnore
    @OneToMany(mappedBy = "casa", fetch = FetchType.LAZY)
    private List<Estudiante> estudiantes;

    /*
     * CAMBIO (según diagrama): Casa <-> Profesor es 1:1 (jefe de casa).
     * Como la FK esta en CASA (columna id_jefe), este es el lado propietario.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jefe", referencedColumnName = "id_profesor", insertable = false, updatable = false)
    private Profesor jefe;
}
