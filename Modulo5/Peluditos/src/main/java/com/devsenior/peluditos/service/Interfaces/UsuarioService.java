package com.devsenior.peluditos.service.Interfaces;

import com.devsenior.peluditos.DTO.UsuarioRequest;
import com.devsenior.peluditos.DTO.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> listarTodos();
    UsuarioResponse crear(UsuarioRequest request);
}
