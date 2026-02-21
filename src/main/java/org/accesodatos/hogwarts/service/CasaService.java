package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.CasaDTO;
import org.accesodatos.hogwarts.mapper.CasaMapper;
import org.accesodatos.hogwarts.model.Casa;
import org.accesodatos.hogwarts.repository.CasaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CasaService {

    private final CasaRepository casaRepository;
    private final CasaMapper casaMapper;

    public CasaService(CasaRepository casaRepository, CasaMapper casaMapper) {
        this.casaRepository = casaRepository;
        this.casaMapper = casaMapper;
    }

    @Transactional(readOnly = true)
    public List<CasaDTO> findAll() {
        return casaRepository.findAll().stream()
                .map(casaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CasaDTO findById(Long id) {
        return casaRepository.findById(id)
                .map(casaMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Casa no existe"));
    }

    @Transactional
    public void delete(Long id) {
        Casa casa = casaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Casa no existe"));
        casaRepository.delete(casa);
    }
}