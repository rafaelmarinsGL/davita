package com.davita.questionnaire.service;

import com.davita.questionnaire.model.Form;
import com.davita.questionnaire.model.Person;
import com.davita.questionnaire.model.Questionnaire;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionnaireService extends CrudRepository<Questionnaire, Integer> {

    List<Questionnaire> findByPerson(Person person);

    List<Questionnaire> findAllByForm(Form form);

}
