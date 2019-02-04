package com.davita.questionnaire.service;

import com.davita.questionnaire.model.Questionnaire;
import org.springframework.data.repository.CrudRepository;

public interface QuestionnaireService extends CrudRepository<Questionnaire, Integer> {
}
