package org.accesodatos.hogwarts.controller;

import org.accesodatos.hogwarts.service.AsignaturaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AsignaturaController.class)
class AsignaturaControllerWebMvcTest {

    @Autowired MockMvc mockMvc;
    @MockBean AsignaturaService asignaturaService;

    @Test
    void eliminarAsignatura_conAlumnos_debeDevolver409() throws Exception {
        Long id = 7L;

        doThrow(new ResponseStatusException(HttpStatus.CONFLICT,
                "No se puede borrar la asignatura: tiene alumnos"))
                .when(asignaturaService).delete(id);

        mockMvc.perform(delete("/hogwarts/asignaturas/{id}", id))
                .andExpect(status().isConflict());
    }
}