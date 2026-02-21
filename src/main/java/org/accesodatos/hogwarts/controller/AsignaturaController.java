package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.accesodatos.hogwarts.dto.AsignaturaDTO;
import org.accesodatos.hogwarts.service.AsignaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Asignaturas", description = "Operaciones sobre asignaturas")
@RestController
@RequestMapping("/hogwarts/asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @Operation(summary = "Obtiene todas las asignaturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public List<AsignaturaDTO> getAll() {
        return asignaturaService.findAll();
    }

    @Operation(summary = "Obtiene una asignatura por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public AsignaturaDTO getById(@PathVariable Long id) {
        return asignaturaService.findById(id);
    }

    @Operation(summary = "Elimina una asignatura por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Eliminado"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        asignaturaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}