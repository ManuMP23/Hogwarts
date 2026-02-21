package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.repository.CasaRepository;
import org.accesodatos.hogwarts.repository.EstudianteAsignaturaRepository;
import org.accesodatos.hogwarts.repository.EstudianteRepository;
import org.accesodatos.hogwarts.mapper.EstudianteMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock EstudianteRepository estudianteRepository;
    @Mock EstudianteMapper estudianteMapper;
    @Mock CasaRepository casaRepository;
    @Mock EstudianteAsignaturaRepository estudianteAsignaturaRepository;

    @InjectMocks
    EstudianteService estudianteService;

    @Test
    void delete_expulsarHarryPotter_debeLlamarDeleteSoloUnaVez() {
        // Given
        Long idHarry = 1L;

        Estudiante harry = new Estudiante();
        harry.setIdEstudiante(idHarry);
        harry.setNombre("Harry");
        harry.setApellido("Potter");

        when(estudianteRepository.findById(idHarry)).thenReturn(Optional.of(harry));

        // When
        estudianteService.delete(idHarry);

        // Then
        verify(estudianteAsignaturaRepository, times(1))
                .deleteByEstudiante_IdEstudiante(idHarry);

        verify(estudianteRepository, times(1)).delete(harry);

        verifyNoMoreInteractions(estudianteRepository, estudianteAsignaturaRepository);
    }
}