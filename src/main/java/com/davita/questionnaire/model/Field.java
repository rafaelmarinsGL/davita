package com.davita.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Field {

    private Integer id;
    private String label;
    private String description;
    private String type;
    private Boolean required;
    private String value;

}
