package com.tfg.taskmanager.dto;

import com.tfg.taskmanager.model.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDTO(UUID id, String username, String email, String password, Role role, String avatar, LocalDateTime createdAt) {}

