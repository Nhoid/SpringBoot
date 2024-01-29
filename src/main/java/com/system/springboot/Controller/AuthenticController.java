package com.system.springboot.Controller;


import com.system.springboot.Infra.TokenService;
import com.system.springboot.User.DTO.LoginInput;
import com.system.springboot.User.DTO.RegisterInput;
import com.system.springboot.User.DTO.TokenOutPut;
import com.system.springboot.User.DTO.UserOutput;
import com.system.springboot.User.User;
import com.system.springboot.User.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthenticController {

    @Autowired
    private AuthenticationManager authentication;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    //METODO RESPONSAVEL PELA LOGICA DO LOGIN
    @PostMapping("/login")
    public ResponseEntity<TokenOutPut> authentic(@RequestBody @Valid LoginInput data){//RECEBE COMO PARAMETRO USERNAME E PASSWORD FORNECIDOS PELO USUARIO E CONVERTIDO PELO DTO
        UsernamePasswordAuthenticationToken usernamePAT = new
                UsernamePasswordAuthenticationToken(data.username(), data.password());//FAZ UM ENCAPSULAMENTO DOS DADOS FORNECIDOS PARA UM OBJETO QUE PODE SER UTILIZADO PELO SISTEMA
        System.out.println("username PAT: " + usernamePAT);
        Authentication authentic =
                authentication.authenticate(usernamePAT);//AQUI ELE PEGA OS DADOS ENCAPSULADOS NA LINHA ANTERIOR E FAZ A REAL ETAPA DE LOGIN, ONDE CONFERE OS DADOS FORNECIDOS E COMPARA COM AS DO BANCO DE DADOS
                                                         //CASO SEJAM FALSOS É LANÇADO UMA EXCEPTION
                                                         //ALÉM DISSO, ESSA CLASSE CARREGA COM ELA OS DADOS DO USUARIO, TANTO OS FORNECIDOS PELA CLASSE UsernamePasswordAuthenticationToken
                                                         //COMO TAMBÉM CARREGA OUTROS DADOS IMPORTANTES DO USUÁRIO LOGADO, COMO ESTADO DE LOGIN, SE ESTÁ OU NÃO AUTHENTICADO, E PRIVILÉGIOS DENTRO DO SISTEMA
                                                         //MAS COMO ESTAMOS TRABALHANDO COM TOKEN ELES NÃO SERÃO MANTIDOS, JÁ QUE NÃO É UMA SESSÃO
        System.out.println("authentic: " + authentic);
        String token =
                tokenService.TokenGenerator((User) authentic.getPrincipal());//SE CHEGAR A ESSE PONTO SIGNIFICA QUE OS DADOS DO USUARIO REALMENTE JÁ ESTÃO VALIDADOS E PODE SER GERADO UM TOKEN DE ACESSO PARA ELE
                                                                             //O TOKEN AQUI GERADO CARREGARÁ AS INFORMAÇÕES DO USUÁRIO E SEUS PODERES NO SISTEMA AO INVÉS DO Authentication
        System.out.println("Token: " + token);

        System.out.println("Chegou até depois da geração do token");
        return ResponseEntity.ok(new TokenOutPut(token));//RETORNA O TOKEN GERADO
    }


    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserOutput> register(@RequestBody @Valid RegisterInput registerInput){//RECEBE OS DADOS ENVIADOS PELO USUARIO

        //FAZ UMA VERIFICAÇÃO SE JA EXISTE ALGUM USUARIO COM O MESMO USERNAME OU EMAIL FORNECIDO
        if (userRepository.findByUsername(registerInput.username()) != null || userRepository.findbyEmail(registerInput.email()) != null) ResponseEntity.badRequest().body("Nome de Usuário já cadastrado");

        //FAZ UMA CRIPTOGRAFIA NA SENHA ANTES DE GUARDAR NO BANCO DE DADOS
        String passwordEncrypt = new BCryptPasswordEncoder().encode(registerInput.password());

        //GERA UMA NOVA INSTANCIA DE USER COM OS DADOS FONECIDOS E A SENHA CRIPTOGRAFADA
        User user = new User(registerInput, passwordEncrypt);
        //SALVA O USUARIO NO SERVIDOR
        userRepository.save(user);


        return ResponseEntity.ok(new UserOutput(user));//RETORNA ALGUNS DADOS DO USUARIO RECEM CADASTRADO, COMO ID, USERNAME E EMAIL

    }




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

}
