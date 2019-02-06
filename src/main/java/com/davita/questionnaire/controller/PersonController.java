package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Person;
import com.davita.questionnaire.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Person Endpoint", description = "CRUD operations for persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/questionnaire/search")
    @ApiOperation(value = "Returns a list of persons filtered by first name and last name")
    Iterable<Person> searchPerson(@RequestParam String firstName, @RequestParam String lastName){
        return personService.findByFirstNameAndLastName(firstName, lastName);
    }
}
