package org.example.playerone.controller;

import org.example.playerone.dto.ApiResponse;
import org.example.playerone.dto.LoginRequest;
import org.example.playerone.security.JwtProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/auth")
public class AuthController {

    private final JwtProvider jwtProvider;

    public AuthController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String token = jwtProvider.generateToken(username);
        return ApiResponse.success(token, "Login successful");
    }

    @GetMapping("/data")
    public ApiResponse<String> getData(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ApiResponse.error("Invalid token format");
        }
        String token = authHeader.substring(7);
        try {
            String username = jwtProvider.getUsername(token);
            if (username != null) {
                return ApiResponse.success(username, "Token validated successfully");
            }
        } catch (Exception e) {
            return ApiResponse.error("Authentication failed: " + e.getMessage());
        }
        return ApiResponse.error("Unauthorized");
    }
}
