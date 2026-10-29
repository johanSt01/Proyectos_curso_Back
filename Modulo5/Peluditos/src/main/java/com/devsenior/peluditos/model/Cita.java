package com.devsenior.peluditos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

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

    // Una agenda sin hora no sirve: la hora es parte de la cita
    private LocalTime hora;

    @Column(nullable = false)
    private String motivo;

    // Ya no es texto libre: solo puede ser uno de tres estados
    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private Double costo;

    // Lo que escribe el veterinario al atender (diagnostico, tratamiento)
    @Column(length = 1000)
    private String notas;

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