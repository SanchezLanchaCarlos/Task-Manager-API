package com.tfg.taskmanager.mapper;

import com.tfg.taskmanager.dto.UserDTO;
import com.tfg.taskmanager.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole(), user.getAvatar(), user.getCreatedAt());
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setAvatar(dto.avatar());
        user.setCreatedAt(dto.createdAt());
        return user;
    }
}

