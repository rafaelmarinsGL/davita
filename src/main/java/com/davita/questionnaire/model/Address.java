package com.davita.questionnaire.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String street;
    private String city;
    private String state;
    private String zip;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Person person;
}
