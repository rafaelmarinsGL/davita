package com.davita.questionnaire.service;

import com.davita.questionnaire.model.Questionnaire;
import com.davita.questionnaire.model.Submission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubmissionService extends CrudRepository<Submission, Integer> {

    List<Submission> findByQuestionnaire(Questionnaire questionnaire);
    List<Submission> findAllByQuestionnaire(List<Questionnaire> questionnaires);
}