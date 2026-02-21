package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.accesodatos.hogwarts.dto.MascotaDTO;
import org.accesodatos.hogwarts.service.MascotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mascotas", description = "Operaciones sobre mascotas")
@RestController
@RequestMapping("/hogwarts/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @Operation(summary = "Obtiene todas las mascotas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public List<MascotaDTO> getAll() {
        return mascotaService.findAll();
    }

    @Operation(summary = "Obtiene una mascota por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public MascotaDTO getById(@PathVariable Long id) {
        return mascotaService.findById(id);
    }

    @Operation(summary = "Elimina una mascota por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Eliminado"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mascotaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}