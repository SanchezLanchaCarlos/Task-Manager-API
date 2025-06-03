package com.tfg.taskmanager.auth;

public record RegisterRequestDTO(String email, String password, String username, String role) {

}
