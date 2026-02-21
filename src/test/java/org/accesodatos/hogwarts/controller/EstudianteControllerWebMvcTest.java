package org.accesodatos.hogwarts.controller;

import org.accesodatos.hogwarts.dto.EstudianteDTO;
import org.accesodatos.hogwarts.service.EstudianteService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudianteService estudianteService;

    @Test
    void crearEstudiante_conAnyoCurso10_debeDevolver400() throws Exception {

        String body = """
        {
          "nombre": "Hermione",
          "apellido": "Granger",
          "anyoCurso": 10,
          "fechaNacimiento": "1980-09-19",
          "casaId": 1,
          "mascota": {
            "nombre": "Crookshanks",
            "especie": "Gato"
          }
        }
        """;

        mockMvc.perform(post("/api/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void crearEstudiante_valido_debeDevolver201YJsonCorrecto() throws Exception {

        // Given
        EstudianteDTO response = new EstudianteDTO();
        response.setId(null);
        response.setNombre("Hermione Granger");
        response.setAnyoCurso(3);

        when(estudianteService.create(any())).thenReturn(response);

        String body = """
        {
          "nombre": "Hermione",
          "apellido": "Granger",
          "anyoCurso": 3,
          "fechaNacimiento": "1980-09-19",
          "casaId": 1,
          "mascota": {
            "nombre": "Crookshanks",
            "especie": "Gato"
          }
        }
        """;

        // Then
        mockMvc.perform(post("/api/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Hermione Granger"))
                .andExpect(jsonPath("$.anyoCurso").value(3));
    }
}