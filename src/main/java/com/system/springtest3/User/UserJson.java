package com.system.springtest3.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

public record UserJson(
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @Past
        java.sql.Date borndate) {

}
