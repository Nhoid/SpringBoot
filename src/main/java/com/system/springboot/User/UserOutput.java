package com.system.springboot.User;

public record UserOutput(Integer id, String name, String password ,String username, String email, String borndate) {
    public UserOutput(User user){
        this(user.getId(), user.getName(), user.getPassword(),user.getUsername(), user.getEmail(), user.getStringDate());
    }
}
