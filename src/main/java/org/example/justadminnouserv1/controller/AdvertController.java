package org.example.justadminnouserv1.controller;

import org.example.justadminnouserv1.dto.AdvertRequestDto;
import org.example.justadminnouserv1.entity.Advert;
import org.example.justadminnouserv1.service.AdvertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/advert")
public class AdvertController {
    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @PostMapping
    public ResponseEntity<Advert> save(@RequestBody AdvertRequestDto advertRequestDto){
        return new ResponseEntity<>(advertService.save(advertRequestDto), HttpStatus.CREATED);
    }

}
