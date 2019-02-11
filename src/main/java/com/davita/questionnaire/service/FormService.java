package com.davita.questionnaire.service;

import com.davita.questionnaire.model.Form;
import org.springframework.data.repository.CrudRepository;

public interface FormService extends CrudRepository<Form, Integer>{
}