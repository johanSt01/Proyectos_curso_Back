package com.devsenior.peluditos.DTO;

public record MascotaResponse(
        Long id,
        String nombre,
        String especie,
        String raza,
        Integer edad,
        Long duenoId,
        String duenoNombre
) {}
