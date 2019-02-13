package com.davita.questionnaire;

import com.davita.questionnaire.controller.FormController;
import com.davita.questionnaire.controller.PersonController;
import com.davita.questionnaire.enums.QuestionnaireStatus;
import com.davita.questionnaire.model.*;
import com.davita.questionnaire.service.FormService;
import com.davita.questionnaire.service.PersonService;
import com.davita.questionnaire.service.QuestionnaireService;
import com.davita.questionnaire.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class QuestionnaireApplication {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    SubmissionService submissionService;

    @Autowired
    PersonService personService;

    @Autowired
    FormService formService;

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApplication.class, args);
    }

    @PostConstruct
    private void init() {

        Person person = new Person();
        person.setFirstName("Rafael");
        person.setMiddleName("Marins");
        person.setLastName("Carinha");
        person = personService.save(person);

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
        form = formService.save(form);

        Form form2 = new Form();
        form2.setName("Form # 2");
        ArrayList<FormSection> sections2 = new ArrayList<>(
                Arrays.asList(
                        new FormSection("Section # 2", new ArrayList<Field>(
                                Arrays.asList(
                                        new Field(1, "first name", "Put your first name here", "shortText", true, ""),
                                        new Field(2, "Address", "Put your address name here", "longText", true, "")))),
                        new FormSection("Section # 2", new ArrayList<Field>(
                                Arrays.asList(
                                        new Field(3, "Do you have gmail?", "Check here is you have a gmail account", "checkbox", true, "true"),
                                        new Field(4, "When is your dog's Birthday?", "Put your dog's Birthday here", "date", true, ""))))));

        form2.setSections(sections2);
        form2 = formService.save(form2);

        questionnaireService.save(new Questionnaire(QuestionnaireStatus.PENDING, person, form, Collections.emptyList()));

        questionnaireService.save(new Questionnaire(QuestionnaireStatus.PENDING, person, form2, Collections.emptyList()));

    }

}

