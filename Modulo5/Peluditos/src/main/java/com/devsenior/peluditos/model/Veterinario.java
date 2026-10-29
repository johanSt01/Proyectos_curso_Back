package com.devsenior.peluditos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veterinarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String especialidad;

    @Column(unique = true)
    private String tarjetaProfesional;

    // Cuenta con la que este veterinario inicia sesion (puede no tener)
    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    @JsonIgnore
    private Usuario usuario;

    // Un veterinario atiende muchas citas
    @OneToMany(mappedBy = "veterinario")
    @JsonIgnore
    private List<Cita> citas = new ArrayList<>();
}
