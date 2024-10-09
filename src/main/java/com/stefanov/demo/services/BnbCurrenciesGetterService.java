package com.stefanov.demo.services;

import com.stefanov.demo.entities.Currency;
import com.stefanov.demo.entities.Language;
import com.stefanov.demo.json.Wrapper;
import com.stefanov.demo.services.converters.JsonMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class BnbCurrenciesGetterService {

    @Autowired
    private BnbRestAPIService bnbRestAPI;


    @Autowired
    private WebSocketService wsService;

    @Autowired
    private JsonMapperService jsonMapperService;

    @Autowired
    private DataBaseService dbService;

    public String updateCurrencies() {
        LocalDate bnbCurrencyDate = getCurrenciesAndPersistToDB();

        List<Currency> currencies = dbService.getLastCurrenciesFromDB(bnbCurrencyDate);

        List<Language> languages = currencies.getFirst().getLanguages();

        String jsonStr = jsonMapperService.toJsonString(new Wrapper(currencies, languages));

        wsService.send(jsonStr);

        return jsonStr;
    }

    private LocalDate getCurrenciesAndPersistToDB() {
        Language bulgarian = dbService.createBulgLanguageEntity(bnbRestAPI.getDataInBulgarian());

        List<Currency> currencies = bulgarian.getCurrencies();

        // get the most recent date from BNB:
        LocalDate bnbCurrencyDate = currencies.get(0).getCurrDate();

        if (bnbCurrencyDate.isAfter(dbService.getMostRecentCurrencyDateFromDB())) {
            log.debug("bnbDate is After localRecordsDate.");
            Language english = dbService.createEngLanguageEntity(bnbRestAPI.getDataInEnglish(), currencies);
            dbService.persistToDb(List.of(bulgarian, english));
        } else {
            log.debug("bnbDate is not After localRecordsDate.");
        }

        return bnbCurrencyDate;
    }
}
