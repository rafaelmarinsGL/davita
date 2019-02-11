package com.davita.questionnaire.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldErrorMessage {

    private String field;
    private String message;

}
