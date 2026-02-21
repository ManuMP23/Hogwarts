package org.accesodatos.hogwarts.repository;

import org.accesodatos.hogwarts.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {}
