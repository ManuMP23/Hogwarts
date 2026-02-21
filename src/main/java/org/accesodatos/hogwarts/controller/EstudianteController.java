package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import org.accesodatos.hogwarts.dto.EstudianteCreateDTO;
import org.accesodatos.hogwarts.dto.EstudianteDTO;
import org.accesodatos.hogwarts.dto.EstudianteUpdateDTO;
import org.accesodatos.hogwarts.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Estudiantes", description = "Operaciones sobre estudiantes")
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @Operation(summary = "Obtiene todos los estudiantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public List<EstudianteDTO> getAll() {
        return estudianteService.findAll();
    }

    @Operation(summary = "Obtiene un estudiante por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public EstudianteDTO getById(@PathVariable Long id) {
        return estudianteService.findById(id);
    }

    @Operation(summary = "Crea un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@Valid @RequestBody EstudianteCreateDTO dto) {
        EstudianteDTO created = estudianteService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * no permite modificar nombre/apellido (si lo mandan, debe fallar).
     * mascota puede ser null para borrar.
     */
    @Operation(summary = "Actualiza un estudiante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @PutMapping("/{id}")
    public EstudianteDTO update(@PathVariable Long id, @Valid @RequestBody EstudianteUpdateDTO dto) {
        return estudianteService.update(id, dto);
    }

    @Operation(summary = "Elimina un estudiante por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Eliminado"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estudianteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}