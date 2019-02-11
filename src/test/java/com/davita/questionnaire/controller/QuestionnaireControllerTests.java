package com.davita.questionnaire.controller;

import com.davita.questionnaire.enums.QuestionnaireStatus;
import com.davita.questionnaire.model.Form;
import com.davita.questionnaire.model.Person;
import com.davita.questionnaire.model.Questionnaire;
import com.davita.questionnaire.service.QuestionnaireService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class QuestionnaireControllerTests {

    @Mock
    QuestionnaireService questionnaireServiceMock;

    @InjectMocks
    QuestionnaireController questionnaireController;

    Questionnaire questionnaire;

    @Before
    public void initialize() {
        questionnaire = new Questionnaire(QuestionnaireStatus.PENDING, new Person(), new Form());
        when(questionnaireServiceMock.findById(1)).thenReturn(Optional.empty());
        when(questionnaireServiceMock.findById(2)).thenReturn(Optional.of(questionnaire));
        doNothing().when(questionnaireServiceMock).deleteById(2);
        when(questionnaireServiceMock.save(questionnaire)).thenReturn(questionnaire);
    }

    @Test
    public void testGetQuestionnaireNotFound() {
        ResponseEntity<?> result = questionnaireController.getQuestionnaire(1);
        Mockito.verify(questionnaireServiceMock).findById(1);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testGetQuestionnaireOk() {
        ResponseEntity<?> result = questionnaireController.getQuestionnaire(2);
        Mockito.verify(questionnaireServiceMock).findById(2);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testPostQuestionnaireOk() {
        ResponseEntity<?> result = questionnaireController.postQuestionnaire(questionnaire);
        Mockito.verify(questionnaireServiceMock).save(questionnaire);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testDeleteQuestionnaireNotFound() {
        ResponseEntity<?> result = questionnaireController.deleteQuestionnaire(1);
        Mockito.verify(questionnaireServiceMock).findById(1);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testDeleteQuestionnaireOk() {
        ResponseEntity<?> result = questionnaireController.deleteQuestionnaire(2);
        Mockito.verify(questionnaireServiceMock).findById(2);
        Mockito.verify(questionnaireServiceMock).deleteById(2);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testUpdateQuestionnaireNotFound() {
        ResponseEntity<?> result = questionnaireController.updateQuestionnaire(1, null);
        Mockito.verify(questionnaireServiceMock).findById(1);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testUpdateQuestionnaireOk() {
        ResponseEntity<?> result = questionnaireController.updateQuestionnaire(2, questionnaire);
        Mockito.verify(questionnaireServiceMock).findById(2);
        Mockito.verify(questionnaireServiceMock).save(questionnaire);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
