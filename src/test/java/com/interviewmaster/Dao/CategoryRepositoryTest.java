package com.interviewmaster.Dao;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private PreparationRepo preparationRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    Preparation preparation;
    Preparation preparation1;

    Category category;
    Category category1;

    // Before Test
    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(1);
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


    }

    // After Test
    @AfterEach
    void tearDown() {
        preparation = null;
        this.preparationRepo.deleteAll();
    }


    // Test Cases

    // Test Case for Successful
    @Test
    void testFindByCategory_found() {
        List<Preparation> questionByCategory = this.preparationRepo.findByCategory(category);
        System.out.println("List of Questions - " + questionByCategory);
        System.out.println("List size - " + questionByCategory.size());
        for(Preparation p : questionByCategory) {
            System.out.println("---> " + p);
        }
        assertThat(questionByCategory.get(1).getId()).isEqualTo(preparation1.getId());
        assertThat(questionByCategory.get(1).getQuestion()).isEqualTo(preparation1.getQuestion());
    }
}