package com.todoapp.userauth.dto;

import lombok.Data;

@Data
public class UserSignInOutput {

    private int userId;

    private String username;

    private String generatedToken;

}
