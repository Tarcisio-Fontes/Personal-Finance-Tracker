package com.example.test.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRegistrationDTO(@NotBlank String name, @NotBlank String password, @NotBlank String email) {
}