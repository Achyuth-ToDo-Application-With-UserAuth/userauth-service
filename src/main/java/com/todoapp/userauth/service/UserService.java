package com.todoapp.userauth.service;


import com.todoapp.userauth.Exceptions.InvalidUsernameOrPasswordException;
import com.todoapp.userauth.dto.UserSignIn;
import com.todoapp.userauth.dto.UserSignInOutput;
import com.todoapp.userauth.dto.UserSignUp;
import com.todoapp.userauth.models.User;
import com.todoapp.userauth.repository.UserRepo;
import com.todoapp.userauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User createUser(UserSignUp userSignUp){
        User user = new User();
        user.setUsername(userSignUp.getUsername());
        user.setEmail(userSignUp.getEmail());

        String password = bCryptPasswordEncoder.encode(userSignUp.getPassword());

        user.setPassword(password);
        return userRepo.save(user);

    }


    public UserSignInOutput userSignIn(UserSignIn userSignIn) throws  RuntimeException{

        User user = userRepo.findByUsername(userSignIn.getUsername());

        UserSignInOutput userSignInOutput = new UserSignInOutput();

        if(bCryptPasswordEncoder.matches(userSignIn.getPassword(), user.getPassword())){
            userSignInOutput.setUserId(user.getUserId());
            userSignInOutput.setUsername(user.getUsername());
            String jwtToken = jwtUtil.generateToken(String.valueOf(user.getUserId()));
            userSignInOutput.setGeneratedToken(jwtToken);
        }
        else{
            throw new InvalidUsernameOrPasswordException("You've given invalid username and password");
        }

        return userSignInOutput;
    }





}
