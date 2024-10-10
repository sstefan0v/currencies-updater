package com.stefanov.demo.services;

import com.stefanov.demo.entities.Currency;
import com.stefanov.demo.entities.Language;
import com.stefanov.demo.services.converters.JsonMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BnbCurrenciesGetterServiceTest {

    @Mock
    private BnbRestAPIService bnbRestAPI;

    @Mock
    private DataBaseService dbService;

    @Mock
    private JsonMapperService jsonMapperService;

    @Mock
    private WebSocketService wsService;

    @InjectMocks
    private BnbCurrenciesGetterService bnbCurrenciesGetterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void willPersistCurrenciesIfDbRecordsAreOlderThanBNBData() {
        List<Language> mockLanguages = List.of(new Language());

        when(dbService.getRecentRecordsFromDB(any(LocalDate.class))).thenReturn(mockLanguages);
        when(jsonMapperService.toJsonString(mockLanguages)).thenReturn("{}");
        when(dbService.createBulgLanguageEntity(any())).thenReturn(getTestLanguage());
        when(dbService.createEngLanguageEntity(any(), anyList())).thenReturn(getTestLanguage());
        when(dbService.getMostRecentCurrencyDateFromDB()).thenReturn(LocalDate.of(1,1,1));

        bnbCurrenciesGetterService.updateCurrencies();

        verify(dbService).persistToDb(any());
        verify(dbService).getRecentRecordsFromDB(any());
        verify(jsonMapperService).toJsonString(any());
        verify(wsService).send(any());
    }

    @Test
    void willNotPersistCurrenciesIfDbRecordsAreNewerThanBNBData() {
        List<Language> mockLanguages = List.of(new Language());

        when(dbService.getRecentRecordsFromDB(any(LocalDate.class))).thenReturn(mockLanguages);
        when(jsonMapperService.toJsonString(mockLanguages)).thenReturn("{}");
        when(dbService.createBulgLanguageEntity(any())).thenReturn(getTestLanguage());
        when(dbService.getMostRecentCurrencyDateFromDB()).thenReturn(LocalDate.of(2024,10,10));

        bnbCurrenciesGetterService.updateCurrencies();

        verify(dbService, times(0)).persistToDb(any());
        verify(dbService).getRecentRecordsFromDB(any());
        verify(jsonMapperService).toJsonString(any());
        verify(wsService).send(any());
    }


    public Language getTestLanguage() {

        Language language = new Language();

        language.setGold(100);
        language.setCode("EN");
        language.setLangCode("en");
        language.setRatio("1.234");
        language.setReverseRate("0.567");
        language.setRate("1.23");
        language.setExtraInfo("Test Info");
        language.setCurrDate("2024-10-10");
        language.setTitle("Test Title");
        language.setFStar(5);


        List<Currency> currencies = new ArrayList<>();

        Currency currency1 = new Currency();
        currency1.setCurrDate(LocalDate.of(2024, 9, 9));
        currency1.setCode("USD");
        currency1.setRate(1.1);
        currency1.setReverseRate(0.91);

        currencies.add(currency1);

        Currency currency2 = new Currency();
        currency2.setCurrDate(LocalDate.of(2024, 9, 9));
        currency2.setCode("EUR");
        currency2.setRate(0.85);
        currency2.setReverseRate(1.18);

        currencies.add(currency2);


        language.setCurrencies(currencies);

        return language;
    }
}