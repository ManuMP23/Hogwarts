package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.EstudianteCreateDTO;
import org.accesodatos.hogwarts.dto.EstudianteDTO;
import org.accesodatos.hogwarts.dto.EstudianteUpdateDTO;
import org.accesodatos.hogwarts.mapper.EstudianteMapper;
import org.accesodatos.hogwarts.model.Casa;
import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.Mascota;
import org.accesodatos.hogwarts.repository.CasaRepository;
import org.accesodatos.hogwarts.repository.EstudianteAsignaturaRepository;
import org.accesodatos.hogwarts.repository.EstudianteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final CasaRepository casaRepository;
    private final EstudianteAsignaturaRepository estudianteAsignaturaRepository;


    public EstudianteService(EstudianteRepository estudianteRepository,
                             EstudianteMapper estudianteMapper,
                             CasaRepository casaRepository,
                             EstudianteAsignaturaRepository estudianteAsignaturaRepository) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
        this.casaRepository = casaRepository;
        this.estudianteAsignaturaRepository = estudianteAsignaturaRepository;
    }


    @Transactional(readOnly = true)
    public List<EstudianteDTO> findAll() {
        return estudianteRepository.findAll().stream()
                .map(estudianteMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public EstudianteDTO findById(Long id) {
        return estudianteRepository.findById(id)
                .map(estudianteMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no existe"));
    }

    /**
     * POST /api/estudiantes
     * Crea estudiante + mascota en una sola petición.
     */
    @Transactional
    public EstudianteDTO create(EstudianteCreateDTO dto) {
        Casa casa = casaRepository.findById(dto.getCasaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Casa no existe"));

        Estudiante e = new Estudiante();
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setAnyoCurso(dto.getAnyoCurso());
        e.setFechaNacimiento(dto.getFechaNacimiento());
        e.setCasa(casa);

        Mascota m = new Mascota();
        m.setNombreMascota(dto.getMascota().getNombre());
        m.setEspecie(dto.getMascota().getEspecie());

        // Enlazar ambos lados
        e.setMascota(m);
        m.setEstudiante(e);

        Estudiante saved = estudianteRepository.save(e);
        return estudianteMapper.toDTO(saved);
    }


    @Transactional
    public EstudianteDTO update(Long id, EstudianteUpdateDTO dto) {
        Estudiante e = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no existe"));

        // NO tocar nombre/apellido
        e.setAnyoCurso(dto.getAnyoCurso());
        e.setFechaNacimiento(dto.getFechaNacimiento());

        if (dto.getMascota() == null) {
            // Borrar mascota si existía
            e.setMascota(null);
        } else {
            // Si ya tenía mascota, la actualizamos
            Mascota m = e.getMascota();
            if (m == null) {
                m = new Mascota();
                m.setEstudiante(e);
                e.setMascota(m);
            }

            m.setNombreMascota(dto.getMascota().getNombre());
            m.setEspecie(dto.getMascota().getEspecie());
        }

        Estudiante saved = estudianteRepository.save(e);
        return estudianteMapper.toDTO(saved);
    }

    @Transactional
    public void delete(Long id) {

        Estudiante e = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Estudiante no existe"));

        // Borramos primero las matrículas (tabla intermedia)
        estudianteAsignaturaRepository.deleteByEstudiante_IdEstudiante(id);

        // Mascota se borra automáticamente por cascade + orphanRemoval
        estudianteRepository.delete(e);
    }

}