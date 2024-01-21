package com.system.springboot.Controller;

import com.system.springboot.User.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController //DEFINE QUE ESSA SERÁ UMA CLASSE DE ENDPOINTS
@RequestMapping("/user") //DEFINE QUAL O ENDEREÇO DO ENDPOINT
public class UserController {

    @Autowired
    UserRepository userRepository;

    //CRIA UMA AÇÃO PARA O METODO POST DO ENDPOINT DEFINIDO NESTA CLASSE
    @PostMapping
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public void input(@RequestBody @Valid UserJson userJson){
        userRepository.save(new User(userJson)); //REGISTRA UM USUARIO NO BANCO DE DADOS
    }
    //CRIA UMA RESPOSTA PARA O METODO GET DO ENDPOINT DEFINIDO NESTA CLASSE
    @GetMapping
    public List<UserOutput> output(){
        return userRepository.findAllByActiveTrue().stream().map(UserOutput::new).toList();
    }

    //CRIA UMA RESPOSTA PARA O METODO PUT DO ENDPOINT DEFINIDO NESTA CLASSE
    @PutMapping
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public void update(@RequestBody @Valid UserUpdateJson userUpdateJson){
        var toUpdate = userRepository.getReferenceById(userUpdateJson.id()); //PASSA PARA toUpdate UMA REFERENCIA DE UM REGISTRO DA TABELA
        toUpdate.update(userUpdateJson);//ATUALIZA O REGISTRO COM BASE NO QUE FOI PASSADO
    }
    @DeleteMapping("/{id}")//DEFINE UM ENDPOINT DINAMICO COM BASE NO ID PASSADO
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public void delete(@PathVariable Integer id){ //@PathVariable É O QUE FAZ O ENDPOINT SER DEFINIDO COM BASE NO ID PASSADO
        userRepository.deleteById(id); //DELETA O USUARIO DA TABELA
    }

    @DeleteMapping("desactive/{id}")//CRIA UM ENDPOINT DINAMICO PARA DESATIVAR UM USUARIO
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public void desactive(@PathVariable Integer id){
        User toDesactive = userRepository.getReferenceById(id);//PASSA PARA toDesactive UMA REFERENCIA DE UM REGISTRO DA TABELA
        toDesactive.desactive();//DESATIVA O REGISTRO
    }

    @PutMapping("active/{id}")//CRIA UM ENDPOINT DINAMICO PARA ATIVAR UM USUARIO
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public void active(@PathVariable Integer id){
        User toActive = userRepository.getReferenceById(id);//PASSA PARA toActive UMA REFERENCIA DE UM REGISTRO DA TABELA
        toActive.active();//ATIVA O USUARIO NOVAMENTE
    }
}

