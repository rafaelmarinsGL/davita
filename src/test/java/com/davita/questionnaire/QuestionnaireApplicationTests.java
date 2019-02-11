package com.davita.questionnaire;

import com.davita.questionnaire.controller.PersonController;
import com.davita.questionnaire.controller.QuestionnaireController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionnaireApplicationTests {

    @Autowired
    QuestionnaireController questionnaireController;

    @Autowired
    PersonController personController;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(questionnaireController);
        Assert.assertNotNull(personController);
    }

}

