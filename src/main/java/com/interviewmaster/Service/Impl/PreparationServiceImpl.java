package com.interviewmaster.Service.Impl;

import com.interviewmaster.Dao.CategoryRepository;
import com.interviewmaster.Dao.PreparationRepo;
import com.interviewmaster.Exceptions.ResourceNotFoundException;
import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import com.interviewmaster.Payload.PreparationDto;
import com.interviewmaster.Service.PreparationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PreparationServiceImpl implements PreparationService {

    @Autowired
    private PreparationRepo prepRepo;

    @Autowired
    private CategoryRepository catRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PreparationDto> getAllQuestions() {
        List<Preparation> allQuations = this.prepRepo.findAll();
        List<PreparationDto> allQuestionsDto = allQuations.stream().map(question -> this.modelMapper.map(question, PreparationDto.class)).collect(Collectors.toList());
        return allQuestionsDto;
    }

    @Override
    public PreparationDto getQuestionById(int id) {
        Preparation question = this.prepRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));
        return this.modelMapper.map(question, PreparationDto.class);
    }

    @Override
    public PreparationDto addNewQuestion(PreparationDto preparationDto, int categoryId) {
        Category category = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Preparation question = this.modelMapper.map(preparationDto, Preparation.class);
        question.setCorrect(false);
        question.setCategory(category);
        Preparation saveQuestion = this.prepRepo.save(question);
        return this.modelMapper.map(saveQuestion, PreparationDto.class);
    }

    @Override
    public PreparationDto updateQuestion(int id, PreparationDto preparationDto) {
        Preparation questionToUpdate = this.prepRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));
        questionToUpdate.setQuestion(preparationDto.getQuestion());
        questionToUpdate.setAnswer(preparationDto.getAnswer());
        System.out.println("IsCorrect - " + preparationDto.isCorrect());
        questionToUpdate.setCorrect(preparationDto.isCorrect());
        this.prepRepo.save(questionToUpdate);
        return this.modelMapper.map(questionToUpdate, PreparationDto.class);
    }

    @Override
    public void deleteQuestion(int id) {
        Preparation question = this.prepRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));
        this.prepRepo.delete(question);
    }

    @Override
    public List<PreparationDto> getAllQuestionsByCategory(int categoryId) {
        Category category = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Preparation> getAllQuestionsByCategory = this.prepRepo.findByCategory(category);

        List<PreparationDto> questionsByCategoryDto = getAllQuestionsByCategory.stream().map(question -> this.modelMapper.map(question, PreparationDto.class)).collect(Collectors.toList());

        return questionsByCategoryDto;
    }

    @Override
    public PreparationDto getRandomQuestionById() {
        Random random = new Random();
        long allDataSize = this.prepRepo.count();
        int randomId = random.nextInt((int)allDataSize) + 1;
        Preparation questionById = this.prepRepo.findById(randomId).orElseThrow(() -> new ResourceNotFoundException("Question", "id", randomId));
        return this.modelMapper.map(questionById, PreparationDto.class);
    }

    @Override
    public List<PreparationDto> searchPreparationByQuestion(String keyword) {
        List<Preparation> questions = this.prepRepo.searchByQuestion("%"+keyword+"%");
        //List<Preparation> questions = this.prepRepo.findPreparationByQuestionLike(keyword);
        List<PreparationDto> questionsDto = questions.stream().map(question -> this.modelMapper.map(question, PreparationDto.class)).collect(Collectors.toList());
        return questionsDto;
    }


}
