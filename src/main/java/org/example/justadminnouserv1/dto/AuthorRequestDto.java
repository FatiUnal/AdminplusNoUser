package org.example.justadminnouserv1.dto;

public record AuthorRequestDto(String name, String username, String password, String confirmedPassword,String biography) {
}
