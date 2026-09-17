package com.devsenior.peluditos.service.Interfaces;

import com.devsenior.peluditos.DTO.VeterinarioRequest;
import com.devsenior.peluditos.DTO.VeterinarioResponse;

import java.util.List;

public interface VeterinarioService {
    List<VeterinarioResponse> listarTodos();
    VeterinarioResponse buscarPorId(Long id);
    VeterinarioResponse crear(VeterinarioRequest request);
    VeterinarioResponse actualizar(Long id, VeterinarioRequest request);
    void eliminar(Long id);
}
