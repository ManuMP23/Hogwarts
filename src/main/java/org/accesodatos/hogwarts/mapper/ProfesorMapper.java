package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.ProfesorDTO;
import org.accesodatos.hogwarts.model.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public ProfesorDTO toDTO(Profesor p) {
        if (p == null) return null;

        String nombreCompleto = p.getNombre() + " " + p.getApellido();
        String asignatura = (p.getAsignatura() != null) ? p.getAsignatura().getNombreAsignatura() : null;

        return new ProfesorDTO(
                p.getIdProfesor(),
                nombreCompleto,
                asignatura,
                p.getFechaInicio()
        );
    }
}
