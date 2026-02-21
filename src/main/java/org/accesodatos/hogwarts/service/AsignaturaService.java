package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.AsignaturaDTO;
import org.accesodatos.hogwarts.mapper.AsignaturaMapper;
import org.accesodatos.hogwarts.model.Asignatura;
import org.accesodatos.hogwarts.repository.AsignaturaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    private final AsignaturaMapper asignaturaMapper;

    public AsignaturaService(AsignaturaRepository asignaturaRepository, AsignaturaMapper asignaturaMapper) {
        this.asignaturaRepository = asignaturaRepository;
        this.asignaturaMapper = asignaturaMapper;
    }

    @Transactional(readOnly = true)
    public List<AsignaturaDTO> findAll() {
        return asignaturaRepository.findAll().stream()
                .map(asignaturaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AsignaturaDTO findById(Long id) {
        return asignaturaRepository.findById(id)
                .map(asignaturaMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no existe"));
    }

    @Transactional
    public void delete(Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no existe"));
        asignaturaRepository.delete(asignatura);
    }
}