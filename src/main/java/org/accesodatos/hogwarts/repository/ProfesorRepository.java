package org.accesodatos.hogwarts.repository;

import org.accesodatos.hogwarts.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    boolean existsByAsignatura_IdAsignatura(Long idAsignatura);
}
