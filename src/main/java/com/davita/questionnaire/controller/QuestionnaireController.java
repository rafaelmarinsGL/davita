package com.davita.questionnaire.controller;

import com.davita.questionnaire.enums.QuestionnaireStatus;
import com.davita.questionnaire.model.*;
import com.davita.questionnaire.service.QuestionnaireService;
import com.davita.questionnaire.service.SubmissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@Api(value = "Questionnaire Endpoint", description = "CRUD operations for questionnaires")
@RequestMapping("/api/v1")
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    SubmissionService submissionService;

    @Autowired
    PersonController personController;

    @Autowired
    FormController formController;

    @GetMapping("/questionnaire")
    @ApiOperation(value = "Get all the questionnaires", notes = "Returns a list of all the questionnaires", response = Questionnaire.class, responseContainer = "List")
    ResponseEntity<?> getQuestionnaires() {
        return ResponseEntity.ok(questionnaireService.findAll());
    }

    @GetMapping("/questionnaire/{id}")
    @ApiOperation(value = "Get a questionnaire by id", notes = "Returns the questionnaire with the given id", response = Questionnaire.class)
    ResponseEntity<?> getQuestionnaire(@PathVariable Integer id) {
        return questionnaireService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/questionnaire")
    @ApiOperation(value = "Create a questionnaire", notes = "Creates a questionnaire and returns the new questionnaire", response = Questionnaire.class)
    ResponseEntity<?> postQuestionnaire(@Valid @RequestBody Questionnaire questionnaire){
        return ResponseEntity.ok(questionnaireService.save(questionnaire));
    }

    @DeleteMapping("/questionnaire/{id}")
    @ApiOperation(value = "Delete a questionnaire", notes = "Deletes the questionnaire with the given id")
    ResponseEntity<?> deleteQuestionnaire(@PathVariable Integer id) {
        return questionnaireService.findById(id)
                .map(q -> {
                    questionnaireService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/questionnaire/{id}")
    @ApiOperation(value = "Update a questionnaire", notes = "Updates the questionnaire with the given id", response = Questionnaire.class)
    ResponseEntity<?> updateQuestionnaire(@PathVariable Integer id, @RequestBody Questionnaire questionnaire) {
        return questionnaireService.findById(id)
                .map(q -> {
                    questionnaire.setId(id);
                    return questionnaireService.save(questionnaire);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/questionnaire/{id}/submit")
    @ApiOperation(value = "Create a submission")
    ResponseEntity<?> submitQuestionnaire(@PathVariable Integer id, @RequestBody Submission submission) {
        return questionnaireService.findById(id)
                .map(q -> {
                    q.setStatus(QuestionnaireStatus.WAITING_REVIEW);
                    return questionnaireService.save(q);
                })
                .map(q -> {
                    submission.setQuestionnaire(q);
                    return ResponseEntity.ok(submissionService.save(submission));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/questionnaire/{id}/submissions")
    @ApiOperation(value = "Get a questionnaire submissions")
    ResponseEntity<?> getQuestionnaireSubmissions(@PathVariable Integer id) {
        return questionnaireService.findById(id)
                .map(q -> submissionService.findByQuestionnaire(q))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostConstruct
    private void init() {

        Person person = new Person();
        person.setFirstName("Rafael");
        person.setMiddleName("Marins");
        person.setLastName("Carinha");
        person = personController.postPerson(person).getBody();

        Form form = new Form();
        form.setName("Form # 1");
        ArrayList<FormSection> sections = new ArrayList<>(
                Arrays.asList(
                        new FormSection("Section # 1", new ArrayList<Field>(
                                Arrays.asList(
                                        new Field(1, "first name", "Put your first name here", "shortText", true, ""),
                                        new Field(2, "Address", "Put your address name here", "longText", true, "")))),
                        new FormSection("Section # 2", new ArrayList<Field>(
                                Arrays.asList(
                                        new Field(3, "Do you have skype?", "Check here is you have a skype account", "checkbox", true, "true"),
                                        new Field(4, "When is your Birthday?", "Put your Birthday here", "date", true, ""))))));

        form.setSections(sections);
        form = formController.postForm(form).getBody();

        postQuestionnaire(new Questionnaire(1, QuestionnaireStatus.PENDING, person, form));

    }
}
