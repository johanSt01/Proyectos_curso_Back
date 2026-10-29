package com.devsenior.peluditos.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaRequest(
        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha,

        @NotNull(message = "La hora es obligatoria")
        LocalTime hora,

        @NotBlank(message = "El motivo es obligatorio")
        String motivo,

        @NotNull(message = "El id de la mascota es obligatorio")
        Long mascotaId,

        @NotNull(message = "El id del veterinario es obligatorio")
        Long veterinarioId
) {}
