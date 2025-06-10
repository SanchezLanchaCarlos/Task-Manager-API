package com.tfg.taskmanager.auth;

import com.tfg.taskmanager.model.User;

public record AuthResponseDTO(String token, User user) {
}
