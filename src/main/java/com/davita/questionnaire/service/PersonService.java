package com.davita.questionnaire.service;

import com.davita.questionnaire.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonService extends CrudRepository<Person, Integer> {

    Iterable<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
