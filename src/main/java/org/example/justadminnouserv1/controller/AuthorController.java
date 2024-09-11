package org.example.justadminnouserv1.controller;

import org.example.justadminnouserv1.dto.AuthorRequestDto;
import org.example.justadminnouserv1.entity.Author;
import org.example.justadminnouserv1.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> register(@RequestBody AuthorRequestDto authorRequestDto){
        return new ResponseEntity<>(authorService.register(authorRequestDto), HttpStatus.CREATED);
    }
}
