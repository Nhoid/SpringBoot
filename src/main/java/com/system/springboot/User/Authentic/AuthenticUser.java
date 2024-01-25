package com.system.springboot.User.Authentic;

import com.system.springboot.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticUser implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    //METODO RESPONSAVEL POR RETORNAR OS DADOS DO USUARIO QUE ESTÁ TENTANDO FAZER LOGIN
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(userRepository.findByUsername(username));
        return userRepository.findByUsername(username);
    }
}
