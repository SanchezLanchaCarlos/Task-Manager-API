package com.tfg.taskmanager.auth;

import com.tfg.taskmanager.model.Role;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.repository.UserRepository;
import com.tfg.taskmanager.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO request) {
        var user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setUsername(request.username());
        try{
            user.setRole(Role.valueOf(request.role()));
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no válido");
        }
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return new AuthResponseDTO(jwt, user);
    }

    @Transactional(readOnly = true)
    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + request.email()));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        var jwt = jwtService.generateToken(user);
        return new AuthResponseDTO(jwt, user);
    }
}

