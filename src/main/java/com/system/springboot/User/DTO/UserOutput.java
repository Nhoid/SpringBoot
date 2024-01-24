package com.system.springboot.User.DTO;


import com.system.springboot.User.User;

//OS PARAMETROS DEFINIDOS AQUI SERÃO OS PARAMETROS ENVIADOS PARA O FRONT-END QUANDO FIZER A REQUISIÇÃO
public record UserOutput(Integer id, String name, String password ,String username, String email, String borndate) {
    public UserOutput(User user){
        //CONSTRUTOR ONDE ELE RECEBE UM OBJETO DO TIPO User E CONVERTE PARA ENVIAR PARA O FRONT-END
        this(user.getId(), user.getName(), user.getPassword(),user.getUsername(), user.getEmail(), user.getStringDate());
    }
}
