package com.davita.questionnaire.model;

import com.davita.questionnaire.util.JpaJsonConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@ApiModel( value = "Submission", description = "Form answers")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Autogenerated id", dataType = "Integer")
    private Integer id;
    @ApiModelProperty(notes = "submit date", dataType = "LocalDateTime")
    private LocalDateTime submitDate;

    @Column(columnDefinition = "text")
    @Convert(converter = JpaJsonConverter.class)
    @ApiModelProperty(notes = "Form answers", dataType = "ArrayList<Answer>")
    private ArrayList<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    private Questionnaire questionnaire;

    public Submission(Integer id, ArrayList<Answer> answers, Questionnaire questionnaire) {
        this.id = id;
        this.answers = answers;
        this.questionnaire = questionnaire;
    }

    public Submission() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public LocalDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDateTime submitDate) {
        this.submitDate = submitDate;
    }
}
