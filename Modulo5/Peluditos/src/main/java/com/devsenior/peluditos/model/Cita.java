package com.devsenior.peluditos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "citas")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String motivo;

    private String estado;
    private Double costo;

    // Cada cita es de una mascota
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    @JsonIgnore
    private Mascota mascota;

    // Cada cita la atiende un veterinario
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;
}