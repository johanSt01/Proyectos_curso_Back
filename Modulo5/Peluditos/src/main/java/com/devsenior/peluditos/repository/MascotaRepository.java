package com.devsenior.peluditos.repository;

import com.devsenior.peluditos.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
