package com.tfg.taskmanager.dto;

import java.util.UUID;
import java.time.LocalDateTime;

public record TaskCommentDTO(UUID id, UUID taskId, UUID userId, String content, LocalDateTime createdAt) {}
