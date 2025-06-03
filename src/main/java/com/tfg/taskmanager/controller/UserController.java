package com.tfg.taskmanager.controller;

import com.tfg.taskmanager.dto.UserDTO;
import com.tfg.taskmanager.mapper.UserMapper;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Users", description = "Gestión de usuarios")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Obtiene todos los usuarios")
    public List<UserDTO> all() {
        return userService.getAllUsers()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un usuario por su ID")
    public ResponseEntity<UserDTO> getById(@PathVariable UUID id) {
        return userService.getById(id)
                .map(UserMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo usuario")
    public UserDTO create(@RequestBody UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toDTO(userService.save(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario por su ID")
    public void delete(@PathVariable UUID id) {
        userService.deleteById(id);
    }
}


