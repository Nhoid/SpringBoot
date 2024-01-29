package com.system.springboot.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

//AQUI É ONDE DEFINIDO OS METODOS DE ACESSO AO BANCO DE DADOS
//EXTENDENDO O JpaRepository ONDE JA TEM TODOS OS METODOS DE ACESSO E MANIPULAÇÃO DO BANCO DE DADOS
//TEMOS PAENAS QUE DEFINIR O TIPO DE OBJETO, QUE É <User> E O TIPO DE DADO DA CHAVE PRIMARIA <Integer>
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    //ENCONTRA APENAS OS USUARIOS ATIVOS
    List<User> findAllByActiveTrue();


    //ENCONTRA USUARIO COM BASE NO USERNAME
    UserDetails findByUsername(String username);

    //ENCONTRA USUARIO COM BASE NO EMAIL
    UserDetails findbyEmail(String email);
}
