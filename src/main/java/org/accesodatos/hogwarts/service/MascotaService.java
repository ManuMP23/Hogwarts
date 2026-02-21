package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.MascotaDTO;
import org.accesodatos.hogwarts.mapper.MascotaMapper;
import org.accesodatos.hogwarts.model.Mascota;
import org.accesodatos.hogwarts.repository.MascotaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final MascotaMapper mascotaMapper;

    public MascotaService(MascotaRepository mascotaRepository, MascotaMapper mascotaMapper) {
        this.mascotaRepository = mascotaRepository;
        this.mascotaMapper = mascotaMapper;
    }

    @Transactional(readOnly = true)
    public List<MascotaDTO> findAll() {
        return mascotaRepository.findAll().stream()
                .map(mascotaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MascotaDTO findById(Long id) {
        return mascotaRepository.findById(id)
                .map(mascotaMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mascota no existe"));
    }

    @Transactional
    public void delete(Long id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mascota no existe"));
        mascotaRepository.delete(mascota);
    }
}