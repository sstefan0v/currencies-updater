package com.stefanov.demo.services.converters;

import com.stefanov.demo.entities.Currency;
import org.springframework.stereotype.Service;
import com.stefanov.demo.controllers.model.RowSet;
import com.stefanov.demo.controllers.model.Row;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrenciesEntityConverter  {

    public List<Currency> toEntity(RowSet rowSet) {
       return rowSet.getRows().stream()
                .filter(row -> row.getTitle() == null )
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public Currency toEntity(Row row) {
        Currency c = new Currency();
        c.setCode(row.getCode());
        c.setGold(parseInt(row.getGold()));
        c.setRatio(parseInt(row.getRatio()));
        c.setReverseRate(parseDouble(row.getReverseRate()));
        c.setRate (parseDouble(row.getRate()));
        c.setFStar (parseInt(row.getFStar()));
        return c;
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
}
