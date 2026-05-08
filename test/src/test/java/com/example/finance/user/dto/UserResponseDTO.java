package com.example.finance.user.dtos;

import com.example.finance.user.entity.User;

public record UserResponseDTO(String id, String name, String password, String email) {

    public static UserResponseDTO fromEntity(User entity) {
        return new UserResponseDTO(entity.getId(), entity.getName(), entity.getPassword(), entity.getEmail());
    }
}
