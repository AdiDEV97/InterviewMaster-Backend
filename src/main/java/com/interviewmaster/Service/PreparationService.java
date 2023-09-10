package com.interviewmaster.Service;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Payload.PreparationDto;

import java.util.List;

public interface PreparationService {

    // Get All Questions
    List<PreparationDto> getAllQuestions();

    // Get Question by Id
    PreparationDto getQuestionById(int id);

    // Add new Question
    PreparationDto addNewQuestion(PreparationDto preparationDto, int categoryId);

    // Update Question
    PreparationDto updateQuestion(int id, PreparationDto preparationDto);

    // Delete Question
    void deleteQuestion(int id);

    // Get All Preparation Questions by Category
    List<PreparationDto> getAllQuestionsByCategory(int categoryId);

    // Get Random Preparation Question
    PreparationDto getRandomQuestionById();

    // Searching Question
    List<PreparationDto> searchPreparationByQuestion(String keyword);

    List<PreparationDto> getQuestionsByMultipleCategories(List<Category> categories);
}
