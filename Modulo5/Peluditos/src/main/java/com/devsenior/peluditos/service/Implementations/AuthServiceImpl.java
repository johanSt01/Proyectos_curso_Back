package com.devsenior.peluditos.service.Implementations;

import com.devsenior.peluditos.DTO.AuthResponse;
import com.devsenior.peluditos.DTO.LoginRequest;
import com.devsenior.peluditos.DTO.RegistroRequest;
import com.devsenior.peluditos.model.Usuario;
import com.devsenior.peluditos.repository.UsuarioRepository;
import com.devsenior.peluditos.service.Interfaces.AuthService;
import com.devsenior.peluditos.service.Interfaces.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse registrar(RegistroRequest request) {
        if (usuarioRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("El username ya esta registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setRol(request.rol());
        usuarioRepository.save(usuario);

        UserDetails userDetails = new User(usuario.getUsername(), usuario.getPassword(), List.of());
        String token = jwtService.generarToken(userDetails, usuario.getRol().name());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // 1. Verifica username + password (lanza excepcion si son incorrectos)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()));

        // 2. Si paso, busca el usuario para leer su rol
        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Genera el token con su rol
        UserDetails userDetails = new User(usuario.getUsername(), usuario.getPassword(), List.of());
        String token = jwtService.generarToken(userDetails, usuario.getRol().name());
        return new AuthResponse(token);
    }
}
