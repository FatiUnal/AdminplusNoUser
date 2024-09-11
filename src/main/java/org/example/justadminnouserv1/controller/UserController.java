package org.example.justadminnouserv1.controller;

import org.example.justadminnouserv1.dto.UserRegisterDto;
import org.example.justadminnouserv1.entity.User;
import org.example.justadminnouserv1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

}
