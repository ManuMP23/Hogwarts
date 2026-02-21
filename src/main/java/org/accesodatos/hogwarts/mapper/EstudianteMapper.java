package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.AsignaturaCalificacionDTO;
import org.accesodatos.hogwarts.dto.EstudianteDTO;
import org.accesodatos.hogwarts.dto.MascotaDTO;
import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.EstudianteAsignatura;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstudianteMapper {

    private final MascotaMapper mascotaMapper;

    public EstudianteMapper(MascotaMapper mascotaMapper) {
        this.mascotaMapper = mascotaMapper;
    }

    public EstudianteDTO toDTO(Estudiante e) {
        if (e == null) return null;

        String nombreCompleto = e.getNombre() + " " + e.getApellido();

        String casa = (e.getCasa() != null) ? e.getCasa().getNombreCasa() : null;
        /*
         * CAMBIO: Relación 1:1 Estudiante <-> Mascota.
         * En el DTO solo debe aparecer UNA mascota (MascotaDTO).
         */
        MascotaDTO mascotaDTO = null;
        if (e.getMascota() != null) {
            mascotaDTO = mascotaMapper.toDTO(e.getMascota());
        }

        /*
         * Asignaturas: se obtienen desde la entidad intermedia EstudianteAsignatura
         * y se transforman a AsignaturaCalificacionDTO (nombre asignatura + nota).
         */
        List<AsignaturaCalificacionDTO> asignaturas = null;
        if (e.getMatriculas() != null) {
            asignaturas = e.getMatriculas().stream()
                    .map(this::toAsignaturaCalificacionDTO)
                    .toList();
        }

        return new EstudianteDTO(
                e.getIdEstudiante(),
                nombreCompleto,
                (e.getAnyoCurso() != null) ? e.getAnyoCurso() : 0,
                e.getFechaNacimiento(),
                casa,
                mascotaDTO,
                asignaturas
        );
    }

    private AsignaturaCalificacionDTO toAsignaturaCalificacionDTO(EstudianteAsignatura ea) {
        String nombreAsig = (ea.getAsignatura() != null) ? ea.getAsignatura().getNombreAsignatura() : null;
        Double nota = (ea.getCalificacion() != null) ? ea.getCalificacion().doubleValue() : null;
        return new AsignaturaCalificacionDTO(nombreAsig, nota);
    }
}
