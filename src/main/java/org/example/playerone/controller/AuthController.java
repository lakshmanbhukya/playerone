package org.example.playerone.controller;

import org.apache.catalina.User;
import org.example.playerone.dto.LoginRequest;
import org.example.playerone.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/auth")
public class AuthController {
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String  username = loginRequest.getUsername();
        String token=jwtProvider.generateToken(username);
        String getUsername=jwtProvider.getUsername(token);
        return String.format("token: %s | getUsername: %s", token, getUsername);
    }

    @GetMapping("/data")
    public String getData(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        try{
            String username=jwtProvider.getUsername(token);
            if(username!=null){
                return String.format("token: %s | getUsername: %s", token, username);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return "unauthorized";
    }

}
