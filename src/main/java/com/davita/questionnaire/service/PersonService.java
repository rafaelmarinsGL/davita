package com.davita.questionnaire.service;

import com.davita.questionnaire.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonService extends CrudRepository<Person, Integer> {

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
