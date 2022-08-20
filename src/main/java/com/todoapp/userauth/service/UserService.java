package com.todoapp.userauth.service;


import com.todoapp.userauth.dto.UserSignUp;
import com.todoapp.userauth.models.User;
import com.todoapp.userauth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(UserSignUp userSignUp){
        User user = new User();
        user.setUsername(userSignUp.getUsername());
        user.setEmail(userSignUp.getEmail());
        user.setPassword(userSignUp.getPassword());
        return userRepo.save(user);
    }





}
