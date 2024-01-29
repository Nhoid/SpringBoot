package com.system.springboot.Controller;

import com.system.springboot.User.*;
import com.system.springboot.User.DTO.UserOutput;
import com.system.springboot.User.DTO.UserUpdateJson;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //DEFINE QUE ESSA SERÁ UMA CLASSE DE ENDPOINTS
@RequestMapping("/user") //DEFINE QUAL O ENDEREÇO DO ENDPOINT
public class UserController {


    @Autowired
    UserRepository userRepository;

    //CRIA UMA AÇÃO PARA O METODO POST DO ENDPOINT DEFINIDO NESTA CLASSE
//    @PostMapping
//    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
//    public ResponseEntity<UserOutput> input(@RequestBody @Valid UserInput userInput, UriComponentsBuilder uriBuilder){
//        User user = new User(userInput); // CONVERTE JSON PARA OBJETO JAVA
//        userRepository.save(user); //REGISTRA UM USUARIO NO BANCO DE DADOS
//
//        return ResponseEntity.created(uriBuilder. //REPONSAVEL POR RETORNAR A URL CORRESPONDENTE AO USUARIO RECEM CRIADO
//                path("/{id}").//DEFINE A URL COM BASE NO ID ATRIBUIDO AO REGISTRO
//                buildAndExpand(user.getId()).
//                toUri()).//CONVERTE PARA A URI
//                body(new UserOutput(user));//RETORNA AS INFORMAÇÕES DO USUARIO CRIADO, JUNTO COM O ID QUE FOI ATRIBUIDO
//    }




    //CRIA UMA RESPOSTA PARA O METODO GET DO ENDPOINT DEFINIDO NESTA CLASSE
    @GetMapping
    public ResponseEntity<List<UserOutput>> output(){
        return ResponseEntity.ok().body(
                userRepository.
                        findAllByActiveTrue().//PROCURA TODOS OS USUARIOS ATIVOS
                        stream().
                        map(UserOutput::new).//MAPEIA TODOS ENCONTRADOS PARA O FORMATO DE SAIDA
                        toList());//COLOCA TODOS EM UMA LISTA
    }

    //CRIA UMA RESPOSTA PARA O METODO PUT DO ENDPOINT DEFINIDO NESTA CLASSE
    @PutMapping
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateJson userUpdateJson){
        var toUpdate = userRepository.getReferenceById(userUpdateJson.id()); //PASSA PARA toUpdate UMA REFERENCIA DE UM REGISTRO DA TABELA
        toUpdate.update(userUpdateJson);//ATUALIZA O REGISTRO COM BASE NO QUE FOI PASSADO

        return ResponseEntity.ok().build();//RETORNA TUDO OK
    }
    @DeleteMapping("/{id}")//DEFINE UM ENDPOINT DINAMICO COM BASE NO ID PASSADO
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public ResponseEntity<Void> delete(@PathVariable Integer id){ //@PathVariable É O QUE FAZ O ENDPOINT SER DEFINIDO COM BASE NO ID PASSADO
        userRepository.deleteById(id.intValue()); //DELETA O USUARIO DA TABELA

        return ResponseEntity.ok().build();//RETORNA TUDO OK
    }

    @DeleteMapping("desactive/{id}")//CRIA UM ENDPOINT DINAMICO PARA DESATIVAR UM USUARIO
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public ResponseEntity<String> desactive(@PathVariable Integer id){
        User toDesactive = userRepository.getReferenceById(id);//PASSA PARA toDesactive UMA REFERENCIA DE UM REGISTRO DA TABELA
        toDesactive.desactive();//DESATIVA O REGISTRO

        return ResponseEntity.ok().body("User %s desactivated".formatted(toDesactive.getName()));//RETORNA TUDO OK E O NOME DO USUARIO DESATIVADO
    }

    @PutMapping("active/{id}")//CRIA UM ENDPOINT DINAMICO PARA ATIVAR UM USUARIO
    @Transactional //SERVE PARA FAZER A ALTERAÇÃO DE UMA FORMA SEGURA
    public ResponseEntity<String> active(@PathVariable Integer id){
        User toActive = userRepository.getReferenceById(id);//PASSA PARA toActive UMA REFERENCIA DE UM REGISTRO DA TABELA
        toActive.active();//ATIVA O USUARIO NOVAMENTE

        return ResponseEntity.ok().body("User %s actived".formatted(toActive.getName()));//RETORNA TUDO OK E O NOME DO USUARIO ATIVADO
    }
}

