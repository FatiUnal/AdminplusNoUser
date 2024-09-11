package org.example.justadminnouserv1.dto;

public record UserRegisterDto(String name, String username, String password, String confirmedPassword, String role) {
}
