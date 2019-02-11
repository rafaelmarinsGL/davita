package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Person;
import com.davita.questionnaire.model.Questionnaire;
import com.davita.questionnaire.service.PersonService;
import com.davita.questionnaire.service.QuestionnaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Person Endpoint", description = "CRUD operations for persons")
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    QuestionnaireService questionnaireService;

    @GetMapping("/person")
    @ApiOperation(value = "Get all the persons")
    ResponseEntity<?> getPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/person/search")
    @ApiOperation(value = "Returns a list of persons filtered by first name and last name")
    ResponseEntity<List<Person>> searchPerson(@RequestParam String firstName, @RequestParam String lastName) {
        List<Person> result = personService.findByFirstNameAndLastName(firstName, lastName);
        return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @PostMapping("/person")
    @ApiOperation(value = "Create a person")
    ResponseEntity<Person> postPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.save(person));
    }

    @GetMapping("/person/{id}/questionnaires")
    @ApiOperation(value = "Returns a list of all pending questionnaires")
    ResponseEntity<List<Questionnaire>> getPersonQuestionnaires(@PathVariable Integer id) {
        return personService.findById(id)
                .map(p -> questionnaireService.findByPerson(p))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
