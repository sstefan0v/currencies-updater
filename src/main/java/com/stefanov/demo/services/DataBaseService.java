package com.stefanov.demo.services;

import com.stefanov.demo.controllers.model.RowsList;
import com.stefanov.demo.entities.Currency;
import com.stefanov.demo.entities.Language;
import com.stefanov.demo.repositories.CurrencyRepository;
import com.stefanov.demo.repositories.LanguageRepository;
import com.stefanov.demo.services.converters.CurrenciesEntityConverter;
import com.stefanov.demo.services.converters.LanguageEntityConverter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.stefanov.demo.services.LanguageCode.BULGARIAN;
import static com.stefanov.demo.services.LanguageCode.ENGLISH;

@Slf4j
@Repository
public class DataBaseService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrenciesEntityConverter currenciesEntityConverter;

    @Autowired
    private LanguageEntityConverter languageEntityConverter;


    List<Currency> getLastCurrenciesFromDB(LocalDate lastDateFromBnb) {
        Currency currency = new Currency();
        currency.setCurrDate(lastDateFromBnb);

        Example<Currency> example = Example.of(currency);
        List<Currency> currencies = currencyRepository.findAll(example);

        log.info("Found {} currencies in DB", currencies.size());

        return currencies;
    }

    @Transactional
    void persistToDb(List<Language> languages) {
        languageRepository.saveAll(languages);
        log.info("Currencies successfully saved to DB.");
    }

    LocalDate getMostRecentCurrencyDateFromDB() {
        Language language = languageRepository.findFirstByOrderByIdDesc();
        if (language == null) {
            return LocalDate.of(1, 1, 1);
        }
        Currency currency = language.getCurrencies().get(0);
        if (currency == null) {
            return LocalDate.of(1, 1, 1);
        }
        return currency.getCurrDate();
    }

    Language createBulgLanguageEntity(RowsList jaxBObjects) {
        List<Currency> currencies = currenciesEntityConverter.toEntity(jaxBObjects);
        Language bulgarian = languageEntityConverter.toEntity(BULGARIAN, jaxBObjects.getRows().get(0));
        bulgarian.setCurrencies(currencies);
        return bulgarian;
    }

    Language createEngLanguageEntity(RowsList jaxBObjects, List<Currency> currencies) {
        Language eng = languageEntityConverter.toEntity(ENGLISH, jaxBObjects.getRows().get(0));
        eng.setCurrencies(currencies);
        return eng;
    }
}