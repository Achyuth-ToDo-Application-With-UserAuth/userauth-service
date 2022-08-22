package com.todoapp.userauth.controller;


import com.todoapp.userauth.dto.UserSignIn;
import com.todoapp.userauth.dto.UserSignInOutput;
import com.todoapp.userauth.dto.UserSignUp;
import com.todoapp.userauth.models.User;
import com.todoapp.userauth.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/")
public class LogInController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody UserSignUp userSignUp) throws Exception{
        User user = userService.createUser(userSignUp);
//        try{
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//            );
//        }
//        catch(Exception e){
//            throw new Exception("Invalid Username or email");
//        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserSignInOutput> signInUser(@RequestBody UserSignIn userSignIn) throws RuntimeException{

        UserSignInOutput userSignInOutput = userService.userSignIn(userSignIn);

        return new ResponseEntity<>(userSignInOutput, HttpStatus.OK);

    }



}
