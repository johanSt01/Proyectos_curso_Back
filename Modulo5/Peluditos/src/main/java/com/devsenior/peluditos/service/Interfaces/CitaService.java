package com.devsenior.peluditos.service.Interfaces;

import com.devsenior.peluditos.DTO.AtencionRequest;
import com.devsenior.peluditos.DTO.CitaRequest;
import com.devsenior.peluditos.DTO.CitaResponse;

import java.util.List;

public interface CitaService {
    List<CitaResponse> listarTodos();
    List<CitaResponse> listarMias(String username);
    List<CitaResponse> listarPorMascota(Long mascotaId);
    CitaResponse buscarPorId(Long id);
    CitaResponse crear(CitaRequest request);
    CitaResponse actualizar(Long id, CitaRequest request);
    CitaResponse atender(Long id, AtencionRequest request, String username);
    CitaResponse cancelar(Long id);
    void eliminar(Long id);
}
