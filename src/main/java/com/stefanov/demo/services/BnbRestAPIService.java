package com.stefanov.demo.services;

import com.stefanov.demo.controllers.model.RowsList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static com.stefanov.demo.services.LanguageCode.BULGARIAN;
import static com.stefanov.demo.services.LanguageCode.ENGLISH;

@Service
@Slf4j
public class BnbRestAPIService extends BnbRestAPIConnector {

    @Autowired
    private JaxBParser jaxBParser;

    public BnbRestAPIService(Props props, WebClient.Builder webBuilder) {
        super(props, webBuilder);
    }

    public RowsList getDataInBulgarian()  {
        String xmlString = fetchXmlData(BULGARIAN).substring(1);
        return jaxBParser.unmarshal(xmlString);
    }

    public RowsList getDataInEnglish()  {
        String xmlString = fetchXmlData(ENGLISH).substring(1);
        return jaxBParser.unmarshal(xmlString);
    }

}
