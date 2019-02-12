package com.davita.questionnaire.service;

import com.davita.questionnaire.model.Submission;
import org.springframework.data.repository.CrudRepository;

public interface SubmissionService extends CrudRepository<Submission, Integer> {
}