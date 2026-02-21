package org.accesodatos.hogwarts.repository;

import org.accesodatos.hogwarts.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    boolean existsByCasa_IdCasa(Long idCasa);
}
