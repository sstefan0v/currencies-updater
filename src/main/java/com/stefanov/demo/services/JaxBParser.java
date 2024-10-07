package com.stefanov.demo.services;

import com.stefanov.demo.controllers.model.RowsList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.StringReader;

@Service
@Slf4j
public class JaxBParser {

    public RowsList unmarshal(String inputString) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(RowsList.class);
        return (RowsList) context.createUnmarshaller()
                .unmarshal(new StringReader(inputString));
    }
}


