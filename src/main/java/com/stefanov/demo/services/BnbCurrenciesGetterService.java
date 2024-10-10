package com.stefanov.demo.services;

import com.stefanov.demo.entities.Currency;
import com.stefanov.demo.entities.Language;
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

        List<Language> languages = dbService.getRecentRecordsFromDB(bnbCurrencyDate);

        String jsonStr = jsonMapperService.toJsonString(languages);

        wsService.send(jsonStr);

        return jsonStr;
    }

    private LocalDate getCurrenciesAndPersistToDB() {
        Language bulgarian = dbService.createBulgLanguageEntity(bnbRestAPI.getDataInBulgarian());

        List<Currency> currencies = bulgarian.getCurrencies();

        // most recent date from BNB:
        LocalDate bnbCurrencyDate = currencies.get(0).getCurrDate();

        // get English data only if already not in the DB:
        if (bnbCurrencyDate.isAfter(dbService.getMostRecentCurrencyDateFromDB())) {
            log.debug("bnbCurrencyDate is After localRecordsDate.");
            Language english = dbService.createEngLanguageEntity(bnbRestAPI.getDataInEnglish(), currencies);
            dbService.persistToDb(List.of(bulgarian, english));
        } else {
            log.debug("bnbCurrencyDate is not After localRecordsDate.");
            log.info("There is record with date {} in DB. Data NOT recorded to DB", bnbCurrencyDate);
        }

        return bnbCurrencyDate;
    }
}
