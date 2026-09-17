package com.devsenior.peluditos.service.Interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generarToken(UserDetails userDetails, String rol);
    String extraerUsername(String token);
    boolean esValido(String token, UserDetails userDetails);
}
