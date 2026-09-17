package com.devsenior.peluditos.service.Interfaces;

import com.devsenior.peluditos.DTO.MascotaRequest;
import com.devsenior.peluditos.DTO.MascotaResponse;

import java.util.List;

public interface MascotaService {
    List<MascotaResponse> listarTodos();
    MascotaResponse buscarPorId(Long id);
    MascotaResponse crear(MascotaRequest request);
    MascotaResponse actualizar(Long id, MascotaRequest request);
    void eliminar(Long id);
}
