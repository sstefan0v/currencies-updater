package com.stefanov.demo.repositories;

import com.stefanov.demo.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    // get the record with the highest 'id'
    public Language findFirstByOrderByIdDesc();
}