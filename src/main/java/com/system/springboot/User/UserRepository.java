package com.system.springboot.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;

//AQUI É ONDE DEFINIDO OS METODOS DE ACESSO AO BANCO DE DADOS
//EXTENDENDO O JpaRepository ONDE JA TEM TODOS OS METODOS DE ACESSO E MANIPULAÇÃO DO BANCO DE DADOS
//TEMOS PAENAS QUE DEFINIR O TIPO DE OBJETO, QUE É <User> E O TIPO DE DADO DA CHAVE PRIMARIA <Integer>
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByActiveTrue();
    UserDetails findByUsername(String username);
}
