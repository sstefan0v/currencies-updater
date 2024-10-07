package com.stefanov.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "currencies")
@Getter
@Setter
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Integer gold;

    @Column( )
    private String code;

    @Column()
    private Integer ratio;

    @Column(name = "reverse_rate")
    private Double reverseRate;

    @Column()
    private Double rate;

    @Column(name = "f_star" )
    private Integer fStar;

    @Column(name = "curr_date")
    private LocalDate currDate;


    @ManyToMany(mappedBy = "currencies")
    @Cascade({  CascadeType.ALL})
    private List<Language> languages;

}
