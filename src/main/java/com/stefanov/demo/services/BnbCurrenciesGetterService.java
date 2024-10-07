package com.stefanov.demo.services;

import com.stefanov.demo.controllers.model.RowsList;
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

import java.time.LocalDate;
import java.util.List;

import static com.stefanov.demo.services.LanguageCode.BULGARIAN;
import static com.stefanov.demo.services.LanguageCode.ENGLISH;

@Service
@Slf4j
public class BnbCurrenciesGetterService {

    @Autowired
    private BNBRestAPIService bnbRestAPI;

    @Autowired
    private JaxBParser jaxBParser;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CurrenciesEntityConverter currenciesEntityConverter;

    @Autowired
    private LanguageEntityConverter languageEntityConverter;


    public String work() throws JAXBException {
        RowsList currenciesListBg = jaxBParser.unmarshal(bnbRestAPI.fetchXmlData(BULGARIAN).substring(1));
        List<Currency> currencies = currenciesEntityConverter.toEntity(currenciesListBg);

        LocalDate bnbDate = currencies.get(0).getCurrDate();

        Language bulgarian = languageEntityConverter.toEntity(BULGARIAN, currenciesListBg.getRows().get(0));

        RowsList currenciesListEng = jaxBParser.unmarshal(bnbRestAPI.fetchXmlData(ENGLISH).substring(1));
        Language english = languageEntityConverter.toEntity(ENGLISH, currenciesListEng.getRows().get(0));


        if (bnbDate.isAfter(getMostRecentCurrencyDateFromDB())) {
            log.debug("bnbDate is After localRecordsDate.");
            persistToDb(List.of(bulgarian, english), currencies);

        } else {
            log.debug("bnbDate is not After localRecordsDate.");
        }

        return "raw";
    }

    @Transactional
    private void persistToDb(List<Language> languages, List<Currency> currencies) {
        languages.forEach(language -> language.setCurrencies(currencies));
        languageRepository.saveAll(languages);

        log.info("Currencies successfully saved to DB.");
    }

    private LocalDate getMostRecentCurrencyDateFromDB() {
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
}
