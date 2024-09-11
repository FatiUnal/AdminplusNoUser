package org.example.justadminnouserv1.dto;

import org.example.justadminnouserv1.entity.Role;

import java.util.List;
import java.util.Set;

public record LoginResponseDto (String name, String username, List<String> roles){
}
