package com.stefanov.demo.services;

import lombok.Getter;

@Getter
public enum LanguageCode {
    BULGARIAN("BG"),
    ENGLISH("EN");

    private final String lang;

    LanguageCode(String lang) {
        this.lang = lang;
    }
}
