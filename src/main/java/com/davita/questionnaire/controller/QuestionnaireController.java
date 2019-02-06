package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Questionnaire;
import com.davita.questionnaire.service.QuestionnaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Api(value = "Questionnaire Endpoint", description = "CRUD operations for questionnaires")
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @GetMapping("/questionnaire/{id}")
    @ApiOperation(value = "Get a questionnaire by id")
    Optional<Questionnaire> getQuestionnaire(@PathVariable Integer id){

        return questionnaireService.findById(id);
    }

    @PostMapping("/questionnaire")
    @ApiOperation(value = "Create a questionnaire")
    Questionnaire postQuestionnaire(@RequestBody Questionnaire questionnaire){
        return questionnaireService.save(questionnaire);
    }

}
