package com.stefanov.demo.services;

import com.stefanov.demo.controllers.model.RowsList;
import com.stefanov.demo.entities.Currency;
import com.stefanov.demo.entities.Language;
import com.stefanov.demo.repositories.LanguageRepository;
import com.stefanov.demo.services.converters.CurrenciesEntityConverter;
import com.stefanov.demo.services.converters.LanguageEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    private CurrenciesEntityConverter currenciesEntityConverter;

    @Autowired
    private LanguageEntityConverter languageEntityConverter;

    List<Language> getRecentRecordsFromDB(LocalDate lastDateFromBnb) {
        Currency currency = new Currency();
        currency.setCurrDate(lastDateFromBnb);

        Language language = new Language();
        language.setCurrencies(List.of(currency));
        List<Language> languages = languageRepository.findAll(Example.of(language));

        log.info("Found {} currency lists (by language) in DB.", languages.size());

        return languages;
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