package com.stefanov.demo.json;

import com.stefanov.demo.entities.Currency;
import com.stefanov.demo.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Wrapper {
    private List<Currency> currencies;

    private List<Language> languages;
}
