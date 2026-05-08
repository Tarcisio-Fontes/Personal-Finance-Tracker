package com.example.finance.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRegistrationDTO(@NotBlank String name, @NotBlank String password, @NotBlank String email) {
}