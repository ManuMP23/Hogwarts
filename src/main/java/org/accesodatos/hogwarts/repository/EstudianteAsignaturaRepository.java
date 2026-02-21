package org.accesodatos.hogwarts.repository;

import org.accesodatos.hogwarts.model.EstudianteAsignatura;
import org.accesodatos.hogwarts.model.EstudianteAsignaturaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteAsignaturaRepository
        extends JpaRepository<EstudianteAsignatura, EstudianteAsignaturaId> {

    boolean existsByEstudiante_IdEstudiante(Long idEstudiante);
    boolean existsByAsignatura_IdAsignatura(Long idAsignatura);

    void deleteByEstudiante_IdEstudiante(Long idEstudiante);
    void deleteByAsignatura_IdAsignatura(Long idAsignatura);
}
