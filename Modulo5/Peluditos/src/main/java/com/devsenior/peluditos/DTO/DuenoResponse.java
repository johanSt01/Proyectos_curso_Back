package com.devsenior.peluditos.DTO;

public record DuenoResponse(
        Long id,
        String nombre,
        String apellido,
        String documento,
        String telefono,
        String email
) {}
