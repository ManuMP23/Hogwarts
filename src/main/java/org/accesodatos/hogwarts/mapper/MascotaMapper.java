package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.MascotaDTO;
import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.Mascota;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {

    public MascotaDTO toDTO(Mascota m) {
        if (m == null) return null;

        Estudiante e = m.getEstudiante();
        String duenyo = (e != null) ? (e.getNombre() + " " + e.getApellido()) : null;

        return new MascotaDTO(
                m.getIdMascota(),
                m.getNombreMascota(),
                m.getEspecie(),
                duenyo
        );
    }
}
