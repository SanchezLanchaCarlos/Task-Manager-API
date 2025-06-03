package com.tfg.taskmanager.dto;

import com.tfg.taskmanager.model.Role;

import java.util.UUID;

public record UserDTO(UUID id, String username, String email, String password, Role role) {}

