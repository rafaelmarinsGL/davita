package com.davita.questionnaire.model;


import java.util.ArrayList;

public class FormSection {

    public FormSection(String name, ArrayList<Field> fields) {
        this.name = name;
        this.fields = fields;
    }

    public FormSection() {
    }

    private String name;

    private ArrayList<Field> fields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }
}
