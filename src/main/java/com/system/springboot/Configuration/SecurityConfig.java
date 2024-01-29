package com.system.springboot.Configuration;

import com.system.springboot.Infra.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((AbstractHttpConfigurer::disable)); //DESATIVA SEGURANÇA CONTRA CSRF, NÃO É TÃO UTIL NESSE TIPO DE API


        httpSecurity.authorizeHttpRequests((authorization) ->
                authorization.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()//PERMITE QUE O LOGIN POSSA SER FEITO SEM TOKEN
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()//PERMITE O REGISTRO DE NOVOS USUARIOS SEM A NECESSIDADE DE TOKENS
                        .requestMatchers(HttpMethod.GET, "/Status").permitAll()//PERMITE QUE ENVIEM SOLICITAÇÃO PARA CHECAR O STATUS DA API
                        .anyRequest().authenticated());//E DEFINE QUE TODOS OS OUTROS DEVEM SER AUTENTICADOS


        httpSecurity.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);//CONFIGURA QUAIS SÃO OS FILTROS QUE OS DADOS DEVERÃO SER VALIDADOS


        httpSecurity.sessionManagement((session) ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//DEFINE O TIPO DE SEÇÃO COMO STATELESS

//        a expressão "stateless" refere-se à característica fundamental do protocolo,
//        que é a falta de estado entre requisições.
//        Isso significa que cada requisição HTTP é independente das anteriores,
//        e o servidor trata cada uma delas isoladamente,
//        sem reter informações sobre requisições anteriores.

        return  httpSecurity.build();
    }

    @Bean//CONFIGURAÇÃO DO AuthenticationManager
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean//ATIVA O BCRUPT ENCONDER, VAI SER RESPONSAVEL POR CRIPTOGRAFAR AS SENHAS ANTES  DE COLOCA-LAS NO BANCO DE DADOS
         //ASSIM COMO TAMBÉM SERÁ RESPONSAVEL POR DESCRIPTOGRAFAR PARA FAZER A VERIFICAÇÃO
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
