package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.accesodatos.hogwarts.dto.CasaDTO;
import org.accesodatos.hogwarts.service.CasaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Casas", description = "Operaciones sobre casas de Hogwarts")
@RestController
@RequestMapping("/hogwarts/casas")
public class CasaController {

    private final CasaService casaService;

    public CasaController(CasaService casaService) {
        this.casaService = casaService;
    }

    @Operation(summary = "Obtiene todas las casas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public List<CasaDTO> getAll() {
        return casaService.findAll();
    }

    @Operation(summary = "Obtiene una casa por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public CasaDTO getById(@PathVariable Long id) {
        return casaService.findById(id);
    }

    @Operation(summary = "Elimina una casa por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Eliminado"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        casaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}