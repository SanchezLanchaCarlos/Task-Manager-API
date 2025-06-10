package com.tfg.taskmanager.dto;

import com.tfg.taskmanager.model.Role;

import java.util.UUID;

public record ProjectMemberDTO(UUID id, UUID userId, UUID projectId, Role role) {}
