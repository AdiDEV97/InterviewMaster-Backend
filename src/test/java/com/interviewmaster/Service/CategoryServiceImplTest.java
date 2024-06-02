package com.interviewmaster.Service;

import com.interviewmaster.Dao.CategoryRepository;
import com.interviewmaster.Model.Category;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Service.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


//@ExtendWith(MockitoExtension.class) // To Enable all the Mockito Annotations
public class CategoryServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CategoryRepository catRepo;

    @InjectMocks
    private CategoryServiceImpl catServ;

    AutoCloseable autoCloseable;

    Category category1;
    Category category2;
    CategoryDto categoryDto1;
    CategoryDto categoryDto2;

    List<Category> allCategoryList;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this); // To Enable all the Mockito Annotations

        category1 = new Category(1, "Java", "All Java Questions", null);
        category2 = new Category(1, "JPA", "All JPA Questions", null);
        categoryDto1 = new CategoryDto(1, "Java", "All Java Questions");
        categoryDto2 = new CategoryDto(1, "JPA", "All JPA Questions");

        allCategoryList = new ArrayList<>(Arrays.asList(category1, category2));
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
        Mockito.reset(catRepo); // to reset the Mock Object which is created using @Mock annotation
    }

    @Test
    void testAllCategories() {
        when(catRepo.findAll()).thenReturn(allCategoryList);
        when(modelMapper.map(category1, CategoryDto.class)).thenReturn(categoryDto1);

        assertThat(this.catServ.allCategories().get(0).getCategoryTitle()).isEqualTo(allCategoryList.get(0).getCategoryTitle()); // Java
        assertThat(this.catServ.allCategories().get(0).getCategoryDescription()).isEqualTo(allCategoryList.get(0).getCategoryDescription()); // All Java Questions
    }

    @Test
    void testCategoryById() {
        when(catRepo.findById(1)).thenReturn(Optional.of(allCategoryList.get(0)));
        when(modelMapper.map(category1, CategoryDto.class)).thenReturn(categoryDto1);

        System.out.println("Actual - " + this.catServ.categoryById(1).getCategoryId());
        System.out.println("Expected - " + this.allCategoryList.get(0).getCategoryId());
        assertThat(this.catServ.categoryById(1).getCategoryId()).isEqualTo(this.allCategoryList.get(0).getCategoryId());
        assertThat(this.catServ.categoryById(1).getCategoryTitle()).isEqualTo(this.allCategoryList.get(0).getCategoryTitle());

    }

    @Test
    void testNewCategory() {
        when(catRepo.save(category1)).thenReturn(category1); // Mocking catRepo.save() method
        when(modelMapper.map(category1, CategoryDto.class)).thenReturn(categoryDto1); // Mocking modelMapper.map() method
        when(modelMapper.map(categoryDto1, Category.class)).thenReturn(category1); // Mocking modelMapper.map() method

        assertThat(catServ.newCategory(categoryDto1).getCategoryId()).isEqualTo(category1.getCategoryId());
    }

    @Test
    void testUpdateCategory() {
        when(catRepo.findById(category1.getCategoryId())).thenReturn(Optional.of(category1));
        when(catRepo.save(any(Category.class))).thenReturn(category1, category2);
        when(modelMapper.map(category1, CategoryDto.class)).thenReturn(categoryDto2); // IMP

        assertThat(catServ.updateCategory(1, categoryDto2).getCategoryTitle()).isEqualTo(categoryDto2.getCategoryTitle());
    }

    @Test
    void testDeleteCategory() {
        when(this.catRepo.findById(category1.getCategoryId())).thenReturn(Optional.of(category1));
        doNothing().when(this.catRepo).delete(any(Category.class));
        catServ.deleteCategory(1);
    }
}
