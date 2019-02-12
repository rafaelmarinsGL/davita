package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Form;
import com.davita.questionnaire.model.Submission;
import com.davita.questionnaire.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Form Endpoint", description = "CRUD operations for forms")
@RequestMapping("/api/v1")
public class FormController {

    @Autowired
    FormService formService;


    @PostMapping("/form")
    @ApiOperation(value = "Create a form", response = Form.class)
    ResponseEntity<Form> postForm(@RequestBody Form form) {
        return ResponseEntity.ok(formService.save(form));
    }

    @GetMapping("/form/{id}")
    @ApiOperation(value = "Get a form by id", response = Form.class)
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
    @ApiOperation(value = "Update a form", response = Form.class)
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
    @ApiOperation(value = "Get a form submissions", response = Form.class, responseContainer = "List")
    ResponseEntity<List<Submission>> getFormSubmissions(@PathVariable Integer id) {
        return formService.findById(id)
                .map(Form::getSubmissions)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
