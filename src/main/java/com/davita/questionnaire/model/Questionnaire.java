package com.davita.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    private LocalDateTime submitDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "questionnaire")
    private Person person;

    public Questionnaire() {
    }

    public Questionnaire(String content, LocalDateTime submitDate, Person person) {
        this.content = content;
        this.submitDate = submitDate;
        this.person = person;
    }
}
