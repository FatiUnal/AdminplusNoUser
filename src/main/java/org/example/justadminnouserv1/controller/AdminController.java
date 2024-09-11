package org.example.justadminnouserv1.controller;

import org.example.justadminnouserv1.dto.AdminRequestDto;
import org.example.justadminnouserv1.entity.Admin;
import org.example.justadminnouserv1.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> register(@RequestBody AdminRequestDto adminRequestDto){
        return new ResponseEntity<>(adminService.register(adminRequestDto), HttpStatus.CREATED);
    }
}
