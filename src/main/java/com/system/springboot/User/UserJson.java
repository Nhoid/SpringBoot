package com.system.springboot.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

public record UserJson(
        @NotBlank//VALIDAÇÃO, NÃO PERMITE NOME EM BRANCO, OU VAZIOS
        String name,
        @Email//VALIDAÇÃO, ACEITA APENAS STRINGS NO FORMATO DE EMAIL
        String email,
        @NotBlank//VALIDAÇÃO, NÃO PERMITE NOME EM BRANCO, OU VAZIOS
        String username,
        @NotBlank//VALIDAÇÃO, NÃO PERMITE NOME EM BRANCO, OU VAZIOS
        String password,
        @Past//VALIDAÇÃO, NÃO PERMITE DATAS A FRENTE DA DATA ATUAL
        java.sql.Date borndate) {

}
