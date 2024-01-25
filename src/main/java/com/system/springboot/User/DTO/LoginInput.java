package com.system.springboot.User.DTO;

import jakarta.validation.constraints.NotNull;

public record LoginInput(
        @NotNull
        String username,
        @NotNull
        String password) {
}
