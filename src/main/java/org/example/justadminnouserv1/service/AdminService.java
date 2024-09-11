package org.example.justadminnouserv1.service;

import org.example.justadminnouserv1.config.App;
import org.example.justadminnouserv1.dto.AdminRequestDto;
import org.example.justadminnouserv1.entity.Admin;
import org.example.justadminnouserv1.entity.Role;
import org.example.justadminnouserv1.exception.NullArgumentException;
import org.example.justadminnouserv1.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public Admin register(AdminRequestDto adminRequestDto){
        if (userService.isUserExist(adminRequestDto.username()))
            throw new NullArgumentException("Bu emaile sahip kullanıcı vardır");

        App.isValidEmail(adminRequestDto.username());
        App.validatePassword(adminRequestDto.password(), adminRequestDto.confirmedPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        Admin admin = new Admin(
                adminRequestDto.name(),
                adminRequestDto.username(),
                passwordEncoder.encode(adminRequestDto.password()),
                roles
        );
        return adminRepository.save(admin);
    }
    
}
