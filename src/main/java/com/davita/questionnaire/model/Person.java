package com.davita.questionnaire.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String ssn;
    private String birthDate;
    private String birthPlace;
    private String birthCountry;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Questionnaire questionnaire;
}
