package org.example.justadminnouserv1.controller;

import org.example.justadminnouserv1.dto.LoginResponseDto;
import org.example.justadminnouserv1.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(){
        return new ResponseEntity<>(authService.login(), HttpStatus.OK);
    }

}
