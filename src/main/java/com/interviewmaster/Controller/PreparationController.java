package com.interviewmaster.Controller;

import com.interviewmaster.Model.Preparation;
import com.interviewmaster.Payload.ApiResponse;
import com.interviewmaster.Payload.PreparationDto;
import com.interviewmaster.Service.PreparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/prep")
public class PreparationController {

    @Autowired
    private PreparationService prepServ;

    // Get all Questions
    @GetMapping("/all-questions")
    public ResponseEntity<List<PreparationDto>> allQuestions() {
        List<PreparationDto> allQuestionsData = this.prepServ.getAllQuestions();
        if(allQuestionsData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.of(Optional.of(allQuestionsData));
        }
    }

    // Get Question by Id
    @GetMapping("/question/id-{id}")
    public ResponseEntity<PreparationDto> getQuestionById(@Valid @PathVariable("id") int id) {
        PreparationDto question = this.prepServ.getQuestionById(id);
        if(question == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(question));
        }
    }


    // Add new question
    @PostMapping("/add-question/categoryId-{categoryId}")
    public ResponseEntity<PreparationDto> addNewQuestion(@Valid @RequestBody PreparationDto preparationDto, @PathVariable("categoryId") int categoryId) {
        PreparationDto newQuestion = this.prepServ.addNewQuestion(preparationDto, categoryId);
        return ResponseEntity.of(Optional.of(newQuestion));
    }


    // Update existing question
    @PutMapping("/update/id-{id}")
    public ResponseEntity<PreparationDto> updateQuestion(@Valid @PathVariable("id") int id, @RequestBody PreparationDto preparationDto) {
        PreparationDto updateQuestion = this.prepServ.updateQuestion(id, preparationDto);
        return ResponseEntity.of(Optional.of(updateQuestion));
    }


    // Delete Question
    @DeleteMapping("/delete/id-{id}")
    public ResponseEntity<ApiResponse> deleteQuestion(@Valid @PathVariable("id") int id) {
        this.prepServ.deleteQuestion(id);
        return new ResponseEntity<>(new ApiResponse(String.format("The product with id - %s is deleted successfully!!", id), true), HttpStatus.OK);
    }

    @GetMapping("/byCategory/id-{categoryId}")
    public ResponseEntity<List<PreparationDto>> getQuestionsByCategory(@Valid @PathVariable("categoryId") int categoryId) {
        List<PreparationDto> questionsByCategory = this.prepServ.getAllQuestionsByCategory(categoryId);
        if(questionsByCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(questionsByCategory));
    }

    @GetMapping("/get-question")
    public ResponseEntity<PreparationDto> getQuestionByRandomId() {
        PreparationDto question = this.prepServ.getRandomQuestionById();
        return ResponseEntity.of(Optional.of(question));
    }
}
