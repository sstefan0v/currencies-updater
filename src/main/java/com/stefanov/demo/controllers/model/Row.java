package com.stefanov.demo.controllers.model;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Row {

    @XmlElement(name = "GOLD")
    private String gold;

    @XmlElement(name = "NAME_")
    private String name;

    @XmlElement(name = "CODE")
    private String code;

    @XmlElement(name = "RATIO")
    private String ratio;

    @XmlElement(name = "REVERSERATE")
    private String reverseRate;

    @XmlElement(name = "RATE")
    private String rate;

    @XmlElement(name = "EXTRAINFO")
    private String extraInfo;

    @XmlElement(name = "CURR_DATE")
    private String currDate;

    @XmlElement(name = "TITLE")
    private String title;

    @XmlElement(name = "F_STAR")
    private String fStar;
}

