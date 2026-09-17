package com.devsenior.peluditos.DTO;

public record VeterinarioResponse(
        Long id,
        String nombre,
        String especialidad,
        String tarjetaProfesional
) {}
