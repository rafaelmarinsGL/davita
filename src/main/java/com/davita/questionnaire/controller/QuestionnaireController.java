package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Person;
import com.davita.questionnaire.model.Questionnaire;
import com.davita.questionnaire.model.Test;
import com.davita.questionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @GetMapping("/questionnaire/{id}")
    Optional<Questionnaire> getQuestionnaire(@PathVariable Integer id){

        return questionnaireService.findById(id);
    }

    @PostMapping("questionnaire")
    Questionnaire postQuestionnaire(@RequestBody Questionnaire questionnaire){

        return questionnaireService.save(questionnaire);
    }

    @PostMapping("test")
    Test post(@RequestBody Test test){
        System.out.println(test);
        return test;
    }
}
