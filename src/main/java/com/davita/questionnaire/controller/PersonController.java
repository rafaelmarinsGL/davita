package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Person;
import com.davita.questionnaire.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Person Endpoint", description = "CRUD operations for persons")
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    @ApiOperation(value = "Get all the persons")
    ResponseEntity<?> getPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/person/search")
    @ApiOperation(value = "Returns a list of persons filtered by first name and last name")
    ResponseEntity<?> searchPerson(@RequestParam String firstName, @RequestParam String lastName) {
        List<Person> result = personService.findByFirstNameAndLastName(firstName, lastName);
        return result.isEmpty() ? ResponseEntity.of(Optional.of(result)).notFound().build() : ResponseEntity.ok(result);
    }
}
