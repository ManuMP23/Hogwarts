package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.ProfesorDTO;
import org.accesodatos.hogwarts.mapper.ProfesorMapper;
import org.accesodatos.hogwarts.model.Profesor;
import org.accesodatos.hogwarts.repository.ProfesorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;

    public ProfesorService(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
    }

    @Transactional(readOnly = true)
    public List<ProfesorDTO> findAll() {
        return profesorRepository.findAll().stream()
                .map(profesorMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProfesorDTO findById(Long id) {
        return profesorRepository.findById(id)
                .map(profesorMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no existe"));
    }

    @Transactional
    public void delete(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no existe"));
        profesorRepository.delete(profesor);
    }
}