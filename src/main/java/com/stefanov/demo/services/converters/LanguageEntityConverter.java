package com.stefanov.demo.services.converters;

import com.stefanov.demo.controllers.model.Row;
import com.stefanov.demo.entities.Language;
import com.stefanov.demo.services.enums.LanguageCode;
import org.springframework.stereotype.Service;

@Service
public class LanguageEntityConverter  {


    public Language toEntity(LanguageCode langCode, Row row) {

        Language language = new Language();
        language.setGold(parseInt(row.getGold()));
        language.setLangCode(langCode.getLang());
        language.setName(row.getName());
        language.setCode(row.getCode());
        language.setRatio(row.getRatio());
        language.setReverseRate(row.getReverseRate());
        language.setRate(row.getRate());
        language.setExtraInfo(row.getExtraInfo());
        language.setCurrDate(row.getCurrDate());
        language.setTitle(row.getTitle());
        language.setFStar(parseInt(row.getFStar()));

        return language;
    }

    private Integer parseInt(String val) {
        if (val == null) {
            return null;
        }
        return Integer.parseInt(val);
    }
}
