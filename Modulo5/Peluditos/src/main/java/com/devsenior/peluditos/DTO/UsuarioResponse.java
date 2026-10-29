package com.devsenior.peluditos.DTO;

public record UsuarioResponse(
        Long id,
        String username,
        String nombre,
        String rol,
        Long veterinarioId
) {}
