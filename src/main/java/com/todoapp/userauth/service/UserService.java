package com.todoapp.userauth.service;


import com.todoapp.userauth.dto.UserSignUp;
import com.todoapp.userauth.models.User;
import com.todoapp.userauth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User createUser(UserSignUp userSignUp){
        User user = new User();
        user.setUsername(userSignUp.getUsername());
        user.setEmail(userSignUp.getEmail());

        String password = bCryptPasswordEncoder.encode(userSignUp.getPassword());

        user.setPassword(password);
        return userRepo.save(user);

    }





}
