package com.devsenior.peluditos.service.Interfaces;

import com.devsenior.peluditos.DTO.DuenoRequest;
import com.devsenior.peluditos.DTO.DuenoResponse;

import java.util.List;

public interface DuenoService {
    List<DuenoResponse> listarTodos();
    DuenoResponse buscarPorId(Long id);
    DuenoResponse crear(DuenoRequest request);
    DuenoResponse actualizar(Long id, DuenoRequest request);
    void eliminar(Long id);
}
