package com.devsenior.peluditos.repository;

import com.devsenior.peluditos.model.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuenoRepository extends JpaRepository<Dueno, Long> {
}
