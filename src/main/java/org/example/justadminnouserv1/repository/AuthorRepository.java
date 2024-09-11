package org.example.justadminnouserv1.repository;

import org.example.justadminnouserv1.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
