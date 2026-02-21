package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.accesodatos.hogwarts.dto.ProfesorDTO;
import org.accesodatos.hogwarts.service.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Profesores", description = "Operaciones sobre profesores")
@RestController
@RequestMapping("/hogwarts/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @Operation(summary = "Obtiene todos los profesores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public List<ProfesorDTO> getAll() {
        return profesorService.findAll();
    }

    @Operation(summary = "Obtiene un profesor por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public ProfesorDTO getById(@PathVariable Long id) {
        return profesorService.findById(id);
    }

    @Operation(summary = "Elimina un profesor por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Eliminado"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profesorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}