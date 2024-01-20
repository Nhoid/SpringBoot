package com.system.springboot.Controller;

import com.system.springboot.User.User;
import com.system.springboot.User.UserJson;
import com.system.springboot.User.UserOutput;
import com.system.springboot.User.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //DEFINE QUE ESSA SERÁ UMA CLASSE DE ENDPOINTS
@RequestMapping("/user") //DEFINE QUAL O ENDEREÇO DO ENDPOINT
public class UserController {

    @Autowired
    UserRepository userRepository;

    //CRIA UMA AÇÃO PARA O METODO POST DO ENDPOINT DEFINIDO NESTA CLASSE
    @PostMapping
    @Transactional
    public void input(@RequestBody @Valid UserJson userJson){
        userRepository.save(new User(userJson)); //REGISTRA UM USUARIO NO BANCO DE DADOS
    }
    //CRIA UMA RESPOSTA PARA O METODO GET DO ENDPOINT DEFINIDO NESTA CLASSE
    @GetMapping
    public List<UserOutput> output(){
        return userRepository.findAll().stream().map(UserOutput::new).toList();
    }
}