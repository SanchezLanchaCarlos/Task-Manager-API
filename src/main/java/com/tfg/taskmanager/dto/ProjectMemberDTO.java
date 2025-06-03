package com.tfg.taskmanager.dto;

import java.util.UUID;

public record ProjectMemberDTO(UUID id, UUID userId, UUID projectId, String role) {}
