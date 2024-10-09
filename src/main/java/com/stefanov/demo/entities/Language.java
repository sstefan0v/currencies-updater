package com.stefanov.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import static org.hibernate.annotations.CascadeType.ALL;

import java.util.List;

@Entity
@Table(name = "languages")
@Getter
@Setter
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer gold;

    @Column
    private String name;

    @Column
    private String code;

    @Column(name = "lang_code")
    private String langCode;

    @Column
    private String ratio;

    @Column(name = "reverse_rate")
    private String reverseRate;

    @Column
    private String rate;

    @Column(name = "extra_info")
    private String extraInfo;

    @Column(name = "curr_date")
    private String currDate;

    @Column
    private String title;

    @Column(name = "f_star")
    private Integer fStar;

    @ManyToMany
    @JoinTable(
            name = "languages_currencies",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id")
    )
    @Cascade({ALL})
    @JsonIgnore
    private List<Currency> currencies;

}
