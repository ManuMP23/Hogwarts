package org.accesodatos.hogwarts.repository;

import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.Mascota;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CascadeDeleteDataJpaTest {

    @Autowired EstudianteRepository estudianteRepository;
    @Autowired MascotaRepository mascotaRepository;

    @Test
    void borrarEstudiante_conMascota_debeBorrarMascotaEnCascada() {
        // Given (campos NOT NULL)
        Estudiante e = new Estudiante();
        e.setNombre("Ron");
        e.setApellido("Weasley");
        e.setFechaNacimiento(LocalDate.of(1980, 3, 1));
        e.setAnyoCurso(3);

        Mascota m = new Mascota();
        m.setNombreMascota("Scabbers");
        m.setEspecie("Rata");

        // enlazar ambos lados
        e.setMascota(m);
        m.setEstudiante(e);

        // guardo SOLO estudiante (cascade guarda mascota)
        Estudiante saved = estudianteRepository.saveAndFlush(e);

        Long idEstudiante = saved.getIdEstudiante();
        assertNotNull(idEstudiante);

        // sacar el id de mascota consultando repo
        Long idMascota = mascotaRepository.findAll().get(0).getIdMascota();

        assertTrue(estudianteRepository.findById(idEstudiante).isPresent());
        assertTrue(mascotaRepository.findById(idMascota).isPresent());

        // When
        estudianteRepository.deleteById(idEstudiante);
        estudianteRepository.flush();

        // Then
        assertTrue(estudianteRepository.findById(idEstudiante).isEmpty());
        assertTrue(mascotaRepository.findById(idMascota).isEmpty(),
                "La mascota debería borrarse en cascada al borrar el estudiante");
    }
}