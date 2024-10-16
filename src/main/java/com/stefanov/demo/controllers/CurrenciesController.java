package com.stefanov.demo.controllers;

import com.stefanov.demo.services.BnbCurrenciesGetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrenciesController {

    @Autowired
    private BnbCurrenciesGetterService bnbCurrenciesGetterService;

    @GetMapping("/download-currencies")
    public String getBnbCurrencies() {
        return bnbCurrenciesGetterService.updateCurrencies();
    }
}
