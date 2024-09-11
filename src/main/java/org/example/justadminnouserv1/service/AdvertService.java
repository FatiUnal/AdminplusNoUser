package org.example.justadminnouserv1.service;

import org.example.justadminnouserv1.dto.AdvertRequestDto;
import org.example.justadminnouserv1.entity.Advert;
import org.example.justadminnouserv1.exception.NullArgumentException;
import org.example.justadminnouserv1.repository.AdvertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public Advert save(AdvertRequestDto advertRequestDto){
        if (advertRequestDto == null)
            throw new NullArgumentException("Reklam bilgileri bulunamadı");

        if (advertRepository.existsByAdvertName(advertRequestDto.advertName()))
            throw new NullArgumentException("Girilen reklam adı mevcuttur");

        Advert advert = new Advert(
                advertRequestDto.advertName(),
                "str",
                advertRequestDto.link()
        );
        return advertRepository.save(advert);
    }

    public List<Advert> getAllAdvert(){
        return advertRepository.findAll();
    }
    public Advert getAdvertByName(String advertName){
        return advertRepository.findByAdvertName(advertName).orElseThrow(()-> new NullArgumentException("Advert not found"));
    }
    public Advert getAdvertById(long id){
        return advertRepository.findById(id).orElseThrow(()-> new NullArgumentException("Advert not found"));
    }
    public String remove(String advertName){
        advertRepository.deleteByAdvertName(advertName);
        return "AdvertDelete";
    }
}
