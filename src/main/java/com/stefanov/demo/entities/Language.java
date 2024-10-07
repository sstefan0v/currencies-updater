package com.stefanov.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

    @Column(name = "extra_info",  columnDefinition = "TEXT")
    private String extraInfo;

    @Column(name = "curr_date")
    private String currDate;

    @Column( columnDefinition = "TEXT")
    private String title;

    @Column(name = "f_star")
    private Integer fStar;

    @ManyToMany
    @JoinTable(
            name = "languages_currencies",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id")
    )
    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    private List<Currency> currencies;

}
