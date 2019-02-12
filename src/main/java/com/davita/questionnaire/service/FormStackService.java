package com.davita.questionnaire.service;

import com.davita.questionnaire.model.Field;
import com.davita.questionnaire.model.Form;
import com.davita.questionnaire.model.FormSection;
import com.davita.questionnaire.model.FormResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FormStackService {

    private String formsUrl = "https://www.formstack.com/api/v2/form.json";

    private Function<Integer, String> fieldsUrl = formId -> String.format("https://www.formstack.com/api/v2/form/%d/field.json", formId);

    private HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer efd38ae6505d1dfdd7ea0b246bf28fb8");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return entity;
    }

    public List<Form> getUserForms() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> headers = getHeaders();

        List<Form> forms = restTemplate.exchange(
                formsUrl,
                HttpMethod.GET,
                headers,
                new ParameterizedTypeReference<FormResponse>() {
                }).getBody().getForms();

        for (Form form : forms) {
            Integer counter = 0;
            ArrayList<FormSection> sections = new ArrayList<>();
            FormSection section = new FormSection("Section # 1", new ArrayList<>());
            sections.add(section);
            List<Field> fields = getFormFields(form.getId());
            for(Field field : fields) {
                if (counter > 0 && counter % 3 == 0) {
                    section = new FormSection("Section # " + ((counter / 3) + 1), new ArrayList<>());
                    sections.add(section);
                }
                section.getFields().add(field);
                counter++;
            }
            form.setSections(sections);
        }

        return forms;
    }

    private List<Field> getFormFields(Integer formId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> headers = getHeaders();

        ResponseEntity<String> response = restTemplate.exchange(
                fieldsUrl.apply(formId),
                HttpMethod.GET,
                headers,
                String.class);
        try {
            return new ObjectMapper().readValue(response.getBody(), new TypeReference<Collection<Field>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
