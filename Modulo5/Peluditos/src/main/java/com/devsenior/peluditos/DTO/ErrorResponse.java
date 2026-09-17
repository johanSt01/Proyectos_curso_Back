package com.devsenior.peluditos.DTO;

import java.time.LocalDateTime;

public record ErrorResponse(
        int codigo,
        String mensaje,
        LocalDateTime fecha
) {}
