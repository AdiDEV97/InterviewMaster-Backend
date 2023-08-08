package com.interviewmaster.Service;

import com.interviewmaster.Model.Preparation;
import com.interviewmaster.Payload.PreparationDto;

import java.util.List;

public interface PreparationService {

    // Get All Questions
    List<PreparationDto> getAllQuestions();

    // Get Question by Id
    PreparationDto getQuestionById(int id);

    // Add new Question
    PreparationDto addNewQuestion(PreparationDto preparationDto);

    // Update Question
    PreparationDto updateQuestion(int id, PreparationDto preparationDto);

    // Delete Question
    void deleteQuestion(int id);
}
