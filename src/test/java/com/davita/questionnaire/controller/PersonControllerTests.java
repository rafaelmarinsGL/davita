package com.davita.questionnaire.controller;

import com.davita.questionnaire.model.Person;
import com.davita.questionnaire.service.PersonService;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PersonControllerTests {

    @Mock
    PersonService personServiceMock;

    @InjectMocks
    PersonController personController;

    Person person;

    @Before
    public void initialize() {
        person = new Person();
        person.setFirstName("Test");
        person.setLastName("Test");
        when(personServiceMock.findAll()).thenReturn(Arrays.asList(new Person(), person, person));
        when(personServiceMock.findByFirstNameAndLastName("Test", "Test")).thenReturn(Arrays.asList(person, person));
    }

    @Test
    public void getPersons() {
        ResponseEntity<?> result = personController.getPersons();
        Mockito.verify(personServiceMock).findAll();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void searchPersonNotFound() {
        ResponseEntity<?> result = personController.searchPerson("", "");
        Mockito.verify(personServiceMock).findByFirstNameAndLastName("", "");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void searchPerson() {
        ResponseEntity<?> result = personController.searchPerson("Test", "Test");
        Mockito.verify(personServiceMock).findByFirstNameAndLastName("Test", "Test");
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
