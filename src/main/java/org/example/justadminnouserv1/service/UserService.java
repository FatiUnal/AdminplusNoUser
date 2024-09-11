package org.example.justadminnouserv1.service;

import org.example.justadminnouserv1.config.App;
import org.example.justadminnouserv1.dto.UserRegisterDto;
import org.example.justadminnouserv1.entity.Role;
import org.example.justadminnouserv1.entity.User;
import org.example.justadminnouserv1.exception.InvalidDataException;
import org.example.justadminnouserv1.exception.NullArgumentException;
import org.example.justadminnouserv1.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }

    public User getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    }
    public User save(UserRegisterDto userRegisterDto){
        if (userRegisterDto == null)
            throw new NullArgumentException("Kayıt formunda eksik bilgi vardır");

        App.isValidEmail(userRegisterDto.username());
        if (userRepository.existsByUsername(userRegisterDto.username()))
            throw new NullArgumentException("Bu emaile sahip kullanıcı vardır");

        App.validatePassword(userRegisterDto.password(), userRegisterDto.confirmedPassword());

        User user = new User();
        user.setName(userRegisterDto.name());
        user.setUsername(userRegisterDto.username());
        user.setPassword(passwordEncoder.encode(userRegisterDto.password()));

        if (userRegisterDto.role().equals("admin")){
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_ADMIN);
            user.setAuthorities(roles);
        } else if (userRegisterDto.role().equals("user")) {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_USER);
            user.setAuthorities(roles);
        } else
            throw new InvalidDataException("Geçersiz rol isteği!");

        return userRepository.save(user);

    }
}
