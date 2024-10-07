package com.stefanov.demo.controllers.model;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Row {

    @XmlElement(name = "GOLD")
    public String gold;

    @XmlElement(name = "NAME_")
    public String name;

    @XmlElement(name = "CODE")
    public String code;

    @XmlElement(name = "RATIO")
    public String ratio;

    @XmlElement(name = "REVERSERATE")
    public String reverseRate;

    @XmlElement(name = "RATE")
    public String rate;

    @XmlElement(name = "EXTRAINFO")
    public String extraInfo;

    @XmlElement(name = "CURR_DATE")
    public String currDate;

    @XmlElement(name = "TITLE")
    public String title;

    @XmlElement(name = "F_STAR")
    public String fStar;
}

