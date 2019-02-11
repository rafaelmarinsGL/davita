package com.davita.questionnaire.model;

public class Field {

    private Integer id;
    private String label;
    private String description;
    private String type;
    private Boolean required;
    private String value;

    public Field(Integer id, String label, String description, String type, Boolean required, String value) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.type = type;
        this.required = required;
        this.value = value;
    }

    public Field() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
