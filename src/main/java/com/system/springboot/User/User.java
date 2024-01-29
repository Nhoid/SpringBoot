package com.system.springboot.User;

import com.system.springboot.User.DTO.RegisterInput;
import com.system.springboot.User.DTO.UserUpdateJson;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;


@Entity//DEFINE ESSA CLASSE COMO UMA ENTIDADE DENTRO DO BANCO DE DADOS
@Data //CRIA, DE FOMRA SIMPLES, TODOS OS GETTERS E SETTERS DE UMA CLASSE, ASSIM COMO O TO STRINGs, OS CONSTRUTORES, MENOS O CONSTRUTOR SEM ARGUMENTO
@NoArgsConstructor(force = true)//CRIA UM CONSTRUTOR SEM ARGUMENTO
@Table(name = "users")//FALA O NOME DA TABELA PARA QUAL ELE ESTÁ APONTANDO DENTRO DO BANCO DE DADOS
public class User implements UserDetails {
    @Id // DEFINE QUE ESSA VARIAVEL É A CHAVE PRIMARIA/ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DEFINE QUE A VARIAVEL ID SERÁ GERADA AUTOMATICAMENTE PELO BANCO DE DADOS
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
    private java.sql.Date borndate;
    private Boolean active;

    //CONSTRUTOR PARA RECEBER DADOS DO TIPO JSON
    public User(RegisterInput registerInput, String passwordEncrypt){
        this.name = registerInput.name();
        this.email = registerInput.email();
        this.username = registerInput.username();
        this.password = passwordEncrypt;
        this.borndate = registerInput.borndate();
        this.active = true;
    }

    //METODO PARA CONVERTER A DATA PARA STRING
    public String getStringDate(){
        return new SimpleDateFormat("yyyy/MM/dd").format(this.borndate);
    }
    public void desactive(){
        this.active = false;
    }//DESATIVA UM USUARIO
    public void active(){this.active = true;}//ATIVA UM USUARIO

    // RECEBE ATUALIZAÇÃO COM BASE NO QUE NÃO VEIO NULL
    public void update(UserUpdateJson userUpdateJson) {
        if (userUpdateJson.name() != null) this.name = userUpdateJson.name();
        if (userUpdateJson.email() != null) this.email = userUpdateJson.email();
        if (userUpdateJson.username() != null) this.username = userUpdateJson.username();
        if (userUpdateJson.password() != null) this.password = new BCryptPasswordEncoder().encode(userUpdateJson.password());
        if (userUpdateJson.active() != null) this.active = userUpdateJson.active();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
