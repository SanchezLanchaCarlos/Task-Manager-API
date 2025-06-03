package com.tfg.taskmanager.dto;

import java.util.UUID;
import java.time.LocalDate;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        String status,
        LocalDate dueDate,
        UUID assigneeId,
        UUID projectId
) {}

