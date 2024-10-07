package com.stefanov.demo.services;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;
import java.io.StringReader;

@Service
@Slf4j

public class JaxBParser {

    public RowSet unmarshal(String inputString) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(RowSet.class);
        return (RowSet) context.createUnmarshaller()
                .unmarshal(new StringReader(inputString));
    }
}


