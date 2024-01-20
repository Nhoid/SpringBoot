package com.system.springboot.User;

import org.springframework.data.jpa.repository.JpaRepository;

//AQUI É ONDE DEFINIDO OS METODOS DE ACESSO AO BANCO DE DADOS
//EXTENDENDO O JpaRepository ONDE JA TEM TODOS OS METODOS DE ACESSO E MANIPULAÇÃO DO BANCO DE DADOS
//TEMOS PAENAS QUE DEFINIR O TIPO DE OBJETO, QUE É <User> E O TIPO DE DADO DA CHAVE PRIMARIA <Integer>
public interface UserRepository extends JpaRepository<User, Integer> {
}
