package org.example.justadminnouserv1.service;

import org.example.justadminnouserv1.config.App;
import org.example.justadminnouserv1.dto.AuthorRequestDto;
import org.example.justadminnouserv1.entity.Author;
import org.example.justadminnouserv1.entity.Role;
import org.example.justadminnouserv1.exception.NullArgumentException;
import org.example.justadminnouserv1.repository.AuthorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthorService(AuthorRepository authorRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public Author register(AuthorRequestDto authorRequestDto){
        if (userService.isUserExist(authorRequestDto.username()))
            throw new NullArgumentException("Bu emaile sahip kullanıcı vardır");

        App.isValidEmail(authorRequestDto.username());
        App.validatePassword(authorRequestDto.password(), authorRequestDto.confirmedPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_AUTHOR);
        Author author = new Author(
                authorRequestDto.name(),
                authorRequestDto.username(),
                passwordEncoder.encode(authorRequestDto.password()),
                roles,
                authorRequestDto.biography()
        );
        return authorRepository.save(author);
    }
}
