package org.example.justadminnouserv1.repository;

import org.example.justadminnouserv1.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertRepository extends JpaRepository<Advert,Long> {
    boolean existsByAdvertName(String advertName);
    Optional<Advert> findByAdvertName(String advertName);
     void deleteByAdvertName(String advertName);
}
