package com.system.springboot.User.DTO;

import jakarta.validation.constraints.NotNull;

//RECEBE OS DADOS QUE O USUARIO QUER ATUALIZAR
public record UserUpdateJson(
        @NotNull// NÃO PERMITE RECEBER UM ID NULO, É POR ELE QUE É IDENTIFICADO O USUARIO A SER ATUALIZADO
        Integer id,
        String name,
        String email ,
        String username,
        String password,
        Boolean active) {

}
