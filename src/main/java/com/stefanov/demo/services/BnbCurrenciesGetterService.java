package com.stefanov.demo.services;


import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;

@Service
@Slf4j
public class BnbCurrenciesGetterService {

    @Autowired
    private BNBRestAPIService bnbRestAPIService;

    @Autowired
    private JaxBParser jaxBParser;

    public String work() throws JAXBException {
      String raw =  bnbRestAPIService.fetchXmlData();
        log.info(raw);

      RowSet rowSet = jaxBParser.unmarshal(raw.substring(1));


      return raw;
    }

}
