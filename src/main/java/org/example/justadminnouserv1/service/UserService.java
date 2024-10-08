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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }

    public User getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    }

    public boolean isUserExist(String username){
        return userRepository.existsByUsername(username);
    }
}
