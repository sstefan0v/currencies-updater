package com.stefanov.demo.services.converters;

import com.stefanov.demo.entities.Currency;
import org.springframework.stereotype.Service;
import com.stefanov.demo.controllers.model.RowSet;
import com.stefanov.demo.controllers.model.Row;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrenciesEntityConverter  {

    private DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public List<Currency> toEntity(RowSet rowSet) {
       return rowSet.getRows().stream()
                .filter(row -> row.getTitle() == null )
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public Currency toEntity(Row row) {
        Currency currency = new Currency();
        currency.setCode(row.getCode());
        currency.setGold(parseInt(row.getGold()));
        currency.setRatio(parseInt(row.getRatio()));
        currency.setReverseRate(parseDouble(row.getReverseRate()));
        currency.setRate (parseDouble(row.getRate()));
        currency.setFStar (parseInt(row.getFStar()));
        currency.setCurrDate(parseDate( row.getCurrDate()));
        return currency;
    }

    private Double parseDouble(String val) {
        if (val == null) {
            return null;
        }
        return Double.parseDouble(val);
    }

    private Integer parseInt(String val) {
        if (val == null) {
            return null;
        }
        return Integer.parseInt(val);
    }

    private LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, dtFormatter);
    }
}
