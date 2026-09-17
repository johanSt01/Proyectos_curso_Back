package com.devsenior.peluditos.service.Interfaces;

import com.devsenior.peluditos.DTO.CitaRequest;
import com.devsenior.peluditos.DTO.CitaResponse;

import java.util.List;

public interface CitaService {
    List<CitaResponse> listarTodos();
    CitaResponse buscarPorId(Long id);
    CitaResponse crear(CitaRequest request);
    CitaResponse actualizar(Long id, CitaRequest request);
    void eliminar(Long id);
}
