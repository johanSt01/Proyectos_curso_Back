package com.devsenior.peluditos.service.Interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioDetailsService {
    UserDetails loadUserByUsername(String username);
}


