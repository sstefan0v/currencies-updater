package com.stefanov.demo.services;

import com.stefanov.demo.controllers.model.RowsList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;

@Service
@Slf4j
public class JaxBParser {

    public RowsList unmarshal(String inputString) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(RowsList.class);
        return (RowsList) context.createUnmarshaller()
                .unmarshal(new StringReader(inputString));
    }

    public <T> String marshal(T rowsList) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(rowsList.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(rowsList, writer);

        return writer.toString();
    }
}


