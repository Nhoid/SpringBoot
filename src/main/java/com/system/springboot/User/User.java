package com.system.springboot.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;


@Entity//DEFINE ESSA CLASSE COMO UMA ENTIDADE DENTRO DO BANCO DE DADOS
@Data //CRIA, DE FOMRA SIMPLES, TODOS OS GETTERS E SETTERS DE UMA CLASSE, ASSIM COMO O TO STRINGM, OS CONSTRUTORES, MENOS O CONSTRUTOR SEM ARGUMENTO
@NoArgsConstructor(force = true)//CRIA UM CONSTRUTOR SEM ARGUMENTO
@Table(name = "users")//FALA O NOME DA TABELA PARA QUAL ELE ESTÁ APONTANDO DENTRO DO BANCO DE DADOS
public class User {
    @Id // DEFINE QUE ESSA VARIAVEL É A CHAVE PRIMARIA/ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DEFINE QUE A VARIAVEL ID SERÁ GERADA AUTOMATICAMENTE PELO BANCO DE DADOS
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
    private java.sql.Date borndate;

    //CONSTRUTOR PARA RECEBER DADOS DO TIPO JSON
    public User(UserJson userJson){

        this.name = userJson.name();
        this.email = userJson.email();
        this.username = userJson.username();
        this.password = userJson.password();
        this.borndate = userJson.borndate();

    }

    //METODO PARA CONVERTER A DATA PARA STRING
    public String getStringDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(this.borndate);
    }
}
