package com.system.springboot.Controller;


import com.system.springboot.Infra.TokenService;
import com.system.springboot.User.Authentic.AuthenticUser;
import com.system.springboot.User.DTO.LoginInput;
import com.system.springboot.User.DTO.TokenOutPut;
import com.system.springboot.User.User;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticController {

    @Autowired
    private AuthenticationManager authentication;

    @Autowired
    private TokenService tokenService;


    //METODO RESPONSAVEL PELA LOGICA DO LOGIN
    @PostMapping
    public ResponseEntity<TokenOutPut> authentic(@RequestBody @Valid LoginInput data){//RECEBE COMO PARAMETRO USERNAME E PASSWORD FORNECIDOS PELO USUARIO E CONVERTIDO PELO DTO
        UsernamePasswordAuthenticationToken usernamePAT = new
                UsernamePasswordAuthenticationToken(data.username(), data.password());//TRANSFORMA OS DADOS PARA UM OBJETO QUE O AUTHENTICATION RECEBE COMO PARAMENTRO
        System.out.println("username PAT: " + usernamePAT);
        Authentication authentic =
                authentication.authenticate(usernamePAT);//PEGA OS DADOS DO USUARIO QUE LOGOU
        System.out.println("authentic: " + authentic);
        String token =
                tokenService.TokenGenerator((User) authentic.getPrincipal());//GERA O TOKEN
        System.out.println("Token: " + token);
        return ResponseEntity.ok(new TokenOutPut(token));//RETORNA O TOKEN GERADO
    }

}
