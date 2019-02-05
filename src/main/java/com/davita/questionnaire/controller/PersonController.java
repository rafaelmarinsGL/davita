package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Person;
import com.davita.questionnaire.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("questionnaire/search")
    Iterable<Person> searchPerson(@RequestParam String firstName, @RequestParam String lastName){
        return personService.findByFirstNameAndLastName(firstName, lastName);
    }
}
