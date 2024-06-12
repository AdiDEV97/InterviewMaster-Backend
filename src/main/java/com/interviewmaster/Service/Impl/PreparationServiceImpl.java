package com.interviewmaster.Service.Impl;

import com.interviewmaster.Dao.CategoryRepository;
import com.interviewmaster.Dao.PreparationRepo;
import com.interviewmaster.Exceptions.ResourceNotFoundException;
import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Payload.InterviewRequisiteDto;
import com.interviewmaster.Payload.PreparationDto;
import com.interviewmaster.Service.PreparationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        question.setCorrect("Not Attempted");
        question.setCategory(category);
        Preparation saveQuestion = this.prepRepo.save(question);
        return this.modelMapper.map(saveQuestion, PreparationDto.class);
    }

    @Override
    public PreparationDto updateQuestion(int id, PreparationDto preparationDto) {
        Preparation questionToUpdate = this.prepRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));
        questionToUpdate.setQuestion(preparationDto.getQuestion());
        questionToUpdate.setAnswer(preparationDto.getAnswer());
        //System.out.println("IsCorrect - " + preparationDto.getCorrect());
        questionToUpdate.setCorrect(preparationDto.getCorrect());
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
        List<PreparationDto> questionsDto = questions.stream().map(question -> this.modelMapper.map(question, PreparationDto.class)).collect(Collectors.toList());
        return questionsDto;
    }

//    @Override
//    public List<PreparationDto> getQuestionsByMultipleCategories(Set<Category> categories) {
//        List<Preparation> allQuestionsByCategories = this.prepRepo.findByCategories(categories);
//        List<PreparationDto> allQuestions = allQuestionsByCategories.stream().map((ques) -> this.modelMapper.map(ques, PreparationDto.class)).collect(Collectors.toList());
//        return allQuestions;
//    }

    @Override
    public List<PreparationDto> getQuestionsByMultipleCategories(InterviewRequisiteDto requisiteDto) {
        /*System.out.println("Selected Topics - " + requisiteDto.getSelectedTopics());
        System.out.println("Get total questions by user - " + requisiteDto.getQuestionCount());
        System.out.println("Get Time for each question - " + requisiteDto.getTime());*/
        //System.out.println("--------------  getQuestionsByMultipleCategories() running...");
        List<Category> selectedTopics = new ArrayList<>();
        for(int i : requisiteDto.getSelectedTopics()) {
            Category cat = this.catRepo.findById(i).orElseThrow(() -> new ResourceNotFoundException("Category", "id", i));

            selectedTopics.add(cat);
        }
        // All Questions Based on Categories selected by User
        List<Preparation> allQuestionsByCategories = this.prepRepo.findByCategories(selectedTopics);
        //System.out.println("---------------- FindByCategories size - " + allQuestionsByCategories.size());
        /*for(Preparation p : allQuestionsByCategories) {
            //System.out.println("--------- Question - " + p.getQuestion());
        }*/

        // Get All Question Ids from allQuestionsByCategories List
        Map<Integer, Preparation> questionsWithId = new HashMap<>();
        int index = 0;
        for(Preparation i : allQuestionsByCategories) {
            index += 1;
            questionsWithId.put(index, i);
        }

        List<Integer> questionByCount = new ArrayList<>();

        // Pick Random id of the Question from QuestionId List
        Set<Integer> generatedId = new HashSet<>();
        List<PreparationDto> questionsToShow = new ArrayList<>();
        int c = 0;
        while (generatedId.size()!=requisiteDto.getQuestionCount() && c!=allQuestionsByCategories.size()) {
            int random = (int) ((Math.random()) * index) + 1;
            if (!generatedId.contains(random)) {
                c = c + 1;
                generatedId.add(random);
                questionsToShow.add(this.modelMapper.map(questionsWithId.get(random), PreparationDto.class));
            }

        }
        return questionsToShow;
    }


    @Override
    public PreparationDto updateStatus(int id, String status) {
        /*Preparation question = this.prepRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Preparation", "id", id));

        System.out.println("Question to change status - " + question);

        *//*question.setQuestion(question.getQuestion());
        question.setAnswer(question.getAnswer());*//*
        question.setCorrect(preparationDto.getCorrect());

        System.out.println("Question to After change - " + question);

        this.prepRepo.save(question);

        return this.modelMapper.map(question, PreparationDto.class);*/

        Preparation questionToUpdate = this.prepRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));
        //questionToUpdate.setQuestion(preparationDto.getQuestion());
        //questionToUpdate.setAnswer(preparationDto.getAnswer());
        //System.out.println("IsCorrect - " + status);
        questionToUpdate.setCorrect(status);
        this.prepRepo.save(questionToUpdate);
        return this.modelMapper.map(questionToUpdate, PreparationDto.class);
    }
}
