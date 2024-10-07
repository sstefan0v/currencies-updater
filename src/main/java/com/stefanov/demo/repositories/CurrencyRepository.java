package com.stefanov.demo.repositories;

import com.stefanov.demo.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}