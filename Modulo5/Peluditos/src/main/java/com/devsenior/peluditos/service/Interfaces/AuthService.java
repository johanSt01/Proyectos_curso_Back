package com.devsenior.peluditos.service.Interfaces;

import com.devsenior.peluditos.DTO.AuthResponse;
import com.devsenior.peluditos.DTO.LoginRequest;
import com.devsenior.peluditos.DTO.RegistroRequest;

public interface AuthService {
    AuthResponse registrar(RegistroRequest request);
    AuthResponse login(LoginRequest request);
}
