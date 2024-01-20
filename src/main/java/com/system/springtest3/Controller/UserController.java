package com.system.springtest3.Controller;

import com.system.springtest3.User.User;
import com.system.springtest3.User.UserJson;
import com.system.springtest3.User.UserOutput;
import com.system.springtest3.User.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    @Transactional
    public String input(@RequestBody @Valid UserJson userJson){
        userRepository.save(new User(userJson));
        return "Usuário registrado com sucesso";
    }

    @GetMapping
    public List<UserOutput> output(){
        return userRepository.findAll().stream().map(UserOutput::new).toList();
    }
}