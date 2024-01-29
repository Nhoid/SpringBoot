package com.system.springboot.Infra;


import com.system.springboot.User.User;
import com.system.springboot.User.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.tool.schema.internal.StandardForeignKeyExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//CLASSE RESPONSAVEL PELA CONFIGURAÇÃO DOS FILTROS DE SEGURANÇA, ONDE SERÁ FEITO A VERIFICAÇÃO DO TOKEN PARA CADA REQUISIÇÃO
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {




        String token = TakeToken(request);//PEGA O TOKEN FORNECIDO PELO USUARIO
        if (token != null){
            String subject = tokenService.getSubject(token);//PEGA O NOME DO USUARIO CORRESPONDENTE AO TOKEN
            UserDetails user = userRepository.findByUsername(subject);//PESQUISA OS DADOS DO USUARIO NO BANCO DE DADOS

            UsernamePasswordAuthenticationToken usernamePAT =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());//TRANSFORMA EM UM OBJETO QUE O SecurityContextHolder RECEBE

            SecurityContextHolder.getContext().setAuthentication(usernamePAT);//FAZ A AUTENTICAÇÃO
        }

        System.out.println("TOKEN: " + token);
        filterChain.doFilter(request, response);
    }

    private String TakeToken(HttpServletRequest request) {//PEGA O TOKEN DA REQUISIÇÃO ENVIADA
        String token = request.getHeader("Authorization");
        if (token != null) return token.replace("Bearer ", "");
        return null;
    }
}
