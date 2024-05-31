package com.interviewmaster.Dao;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PreparationRepositoryTest {

    @Autowired
    private PreparationRepo preparationRepo;

    @Autowired
    private PreparationRepo preparationRepo1;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private CategoryRepository categoryRepo1;

    Preparation preparation;
    Preparation preparation1;

    Category category;
    Category category1;

    // Before Each Test
    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(0);
        category.setCategoryTitle("Java1");
        category.setCategoryDescription("All Java1 Questions");
        this.categoryRepo.save(category);

        category1 = new Category();
        category1.setCategoryId(2);
        category1.setCategoryTitle("Java1");
        category1.setCategoryDescription("All Java1 Questions");
        this.categoryRepo.save(category1);


        preparation = new Preparation();
        preparation.setId(1);
        preparation.setQuestion("What is JPA");
        preparation.setAnswer("<ul><b>JPA (Java Persistence API)</b></ul>");
        preparation.setCorrect("Not Attempted");
        preparation.setCategory(category);
        this.preparationRepo.save(preparation);

        preparation1 = new Preparation();
        preparation1.setId(2);
        preparation1.setQuestion("What is Hibernate");
        preparation1.setAnswer("<ul><b>Hibernate is the implementation of JPA</b></ul>");
        preparation1.setCorrect("Not Attempted");
        preparation1.setCategory(category);
        this.preparationRepo.save(preparation1);
        System.out.println("---------- SetUp Completed --------------");
    }

    // After Each Test
    @AfterEach
    void tearDown() {

        this.preparationRepo.deleteAll();
        this.preparationRepo1.deleteAll();
        this.categoryRepo.deleteAll();
        this.categoryRepo1.deleteAll();
        //this.categoryRepo.save(category);
        //this.preparationRepo.save(preparation);
        /*this.preparation = null;
        preparation1 = null;
        category = null;
        category1 = null;*/
        System.out.println("---------- TearDown Completed --------------");
    }


    // Test Cases

    // ------------------------  FindByCategory --------------------------
    // Test Case for Successful
    @Test
    void testFindByCategory_found() {
        List<Preparation> questionByCategory = this.preparationRepo.findByCategory(category);
        System.out.println("List of Questions - " + questionByCategory);
        System.out.println("List size - " + questionByCategory.size());
        for(Preparation p : questionByCategory) {
            System.out.println("---> " + p);
        }
        assertThat(questionByCategory.get(0).getId()).isEqualTo(preparation.getId());
        assertThat(questionByCategory.get(1).getQuestion()).isEqualTo(preparation1.getQuestion());
    }

    // Test Case for Failure
    @Test
    void testFindByCategory_notFound() {
        System.out.println("test case 2!!!");
        List<Preparation> questionByCategory1 = this.preparationRepo1.findByCategory(category1);
        System.out.println("List of Questions - " + questionByCategory1);
        System.out.println("List size - " + questionByCategory1.size());
        assertThat(questionByCategory1.isEmpty()).isTrue();
    }

    // ---------------------  SearchByQuestion  ----------------------
    // Test Case for Successful
    @Test
    void testSearchByQuestion_found() {
        List<Preparation> listOfQuestions = this.preparationRepo.searchByQuestion("%What%");
        System.out.println("List - " + listOfQuestions);
        System.out.println("Size - " + listOfQuestions.size());
        System.out.println("Expected - " + preparation1.getQuestion());
        System.out.println("Actual - " + listOfQuestions.get(0).getQuestion());

        // assertThat(actual).isEqualTo(expected)
        assertThat(listOfQuestions.get(1).getQuestion()).isEqualTo(preparation1.getQuestion());
    }

    @Test
    void testSearchByQuestion_notFound() {
        List<Preparation> listOfQuestions = this.preparationRepo.searchByQuestion("%spring%");
        System.out.println("List - " + listOfQuestions);
        System.out.println("Size - " + listOfQuestions.size());
        assertThat(listOfQuestions.isEmpty()).isTrue();
    }
}