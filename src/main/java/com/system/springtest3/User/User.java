package com.system.springtest3.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Entity
@Data
@NoArgsConstructor(force = true)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
    private java.sql.Date borndate;
    public User(UserJson userJson){

        this.name = userJson.name();
        this.email = userJson.email();
        this.username = userJson.username();
        this.password = userJson.password();
        this.borndate = userJson.borndate();

    }

    public String getStringDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(this.borndate);
    }
}
