package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.AsignaturaDTO;
import org.accesodatos.hogwarts.model.Asignatura;
import org.accesodatos.hogwarts.model.Profesor;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaMapper {

    public AsignaturaDTO toDTO(Asignatura a) {
        if (a == null) return null;

        Profesor p = null;
        if (a.getProfesores() != null && !a.getProfesores().isEmpty()) {
            p = a.getProfesores().get(0);
        }

        String profesor = (p != null) ? (p.getNombre() + " " + p.getApellido()) : null;


        return new AsignaturaDTO(
                a.getIdAsignatura(),
                a.getNombreAsignatura(),
                a.getAula(),
                a.getObligatoria(),
                profesor
        );
    }
}
