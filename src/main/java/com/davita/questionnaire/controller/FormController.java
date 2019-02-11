package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Form;
import com.davita.questionnaire.model.Submission;
import com.davita.questionnaire.service.FormService;
import com.davita.questionnaire.service.QuestionnaireService;
import com.davita.questionnaire.service.SubmissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Form Endpoint", description = "CRUD operations for forms")
@RequestMapping("/api/v1")
public class FormController {

    @Autowired
    FormService formService;

    @Autowired
    SubmissionService submissionService;

    @Autowired
    QuestionnaireService questionnaireService;

    @PostMapping("/form")
    @ApiOperation(value = "Create a form")
    ResponseEntity<Form> postForm(@RequestBody Form form) {
        return ResponseEntity.ok(formService.save(form));
    }

    @GetMapping("/form/{id}")
    @ApiOperation(value = "Get a form by id")
    ResponseEntity<Form> getForm(@PathVariable Integer id) {
        return formService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/form/{id}")
    @ApiOperation(value = "Delete a form by id")
    ResponseEntity<?> deleteForm(@PathVariable Integer id) {
        return formService.findById(id)
                .map(q -> {
                    formService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/form/{id}")
    @ApiOperation(value = "Update a form")
    ResponseEntity<Form> updateForm(@PathVariable Integer id, @RequestBody Form form) {
        System.out.println(form.toString());
        return formService.findById(id)
                .map(q -> {
                    form.setId(id);
                    return formService.save(form);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/form/{id}/submissions")
    @ApiOperation(value = "Get a form submissions")
    ResponseEntity<List<Submission>> getQuestionnaireSubmissions(@PathVariable Integer id) {
        return formService.findById(id)
                .map(f -> questionnaireService.findAllByForm(f))
                .flatMap(l -> Optional.of(submissionService.findAllByQuestionnaire(l)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
