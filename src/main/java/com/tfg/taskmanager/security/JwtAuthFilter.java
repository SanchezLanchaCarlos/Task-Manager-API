package com.tfg.taskmanager.security;

import com.tfg.taskmanager.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final Map<String, Object> tokenCache = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        final String userEmail = jwtService.extractEmail(token);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Verificar primero en el caché
            var cachedAuth = tokenCache.get(token);
            if (cachedAuth != null) {
                SecurityContextHolder.getContext().setAuthentication((UsernamePasswordAuthenticationToken) cachedAuth);
            } else {
                var user = userRepository.findByEmail(userEmail).orElse(null);

                if (user != null && jwtService.isTokenValid(token, user)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(user, null, List.of());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    // Guardar en caché
                    tokenCache.put(token, authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}