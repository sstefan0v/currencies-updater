package com.stefanov.demo.services;


import com.stefanov.demo.controllers.model.RowSet;
import com.stefanov.demo.entities.Currency;
import com.stefanov.demo.entities.Language;
import com.stefanov.demo.repositories.LanguageRepository;
import com.stefanov.demo.services.converters.CurrenciesEntityConverter;
import com.stefanov.demo.services.converters.LanguageEntityConverter;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class BnbCurrenciesGetterService {

    @Autowired
    private BNBRestAPIService bnbRestAPIService;

    @Autowired
    private JaxBParser jaxBParser;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CurrenciesEntityConverter currenciesEntityConverter;

    @Autowired
    private LanguageEntityConverter languageEntityConverter;


    public String work() throws JAXBException {
        String raw = bnbRestAPIService.fetchXmlData();

        RowSet rowSet = jaxBParser.unmarshal(raw.substring(1));

        Language language = languageEntityConverter.toEntity("BG", rowSet.getRows().get(0));

        List<Currency> currencies = currenciesEntityConverter.toEntity(rowSet);

        persistToDb(List.of(language), currencies);

        return raw;
    }


    @Transactional
    public void persistToDb(List<Language> languages, List<Currency> currencies) {
        languages.forEach(language -> language.setCurrencies(currencies));
        languageRepository.saveAll(languages);

        log.debug("Currencies successfully saved to data base.");
    }
}
