package com.tfg.taskmanager.dto;

import java.util.UUID;

public record ProjectDTO(UUID id, String name, String description, UUID ownerId) {}
