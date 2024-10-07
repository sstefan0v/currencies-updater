package com.stefanov.demo.controllers.model;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;

import java.util.List;

@XmlRootElement(name = "ROWSET")
@Getter
public class RowSet {

    @XmlElement(name = "ROW")
    public List<Row> rows;
}
