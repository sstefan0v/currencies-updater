package com.stefanov.demo.services.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.stefanov.demo.json.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonMapperService {
    private ObjectMapper objectMapper;

    @Autowired
    private JsonMapperService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public String toJsonString(Wrapper wrapper)  {
        try {
            String json = objectMapper.writeValueAsString(wrapper);
            log.debug("Created json string: {}", json);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
