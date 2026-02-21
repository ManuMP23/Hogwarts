package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.CasaDTO;
import org.accesodatos.hogwarts.dto.ProfesorDTO;
import org.accesodatos.hogwarts.model.Casa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CasaMapper {

    private final ProfesorMapper profesorMapper;

    public CasaMapper(ProfesorMapper profesorMapper) {
        this.profesorMapper = profesorMapper;
    }

    public CasaDTO toDTO(Casa c) {
        if (c == null) return null;

        ProfesorDTO jefe = profesorMapper.toDTO(c.getJefe());

        List<String> estudiantes = null;
        if (c.getEstudiantes() != null) {
            estudiantes = c.getEstudiantes().stream()
                    .map(e -> e.getNombre() + " " + e.getApellido())
                    .toList();
        }

        return new CasaDTO(
                c.getIdCasa(),
                c.getNombreCasa(),
                c.getFundador(),
                c.getFantasma(),
                jefe,
                estudiantes
        );
    }
}
