package com.devsenior.peluditos.repository;

import com.devsenior.peluditos.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}
