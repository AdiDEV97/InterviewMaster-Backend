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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


//@ExtendWith(MockitoExtension.class) // To Enable all the Mockito Annotations
public class CategoryServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CategoryRepository catRepo;

    @InjectMocks
    private CategoryServiceImpl catServ;

    AutoCloseable autoCloseable;

    Category category;
    Category category1;
    CategoryDto categoryDto;
    CategoryDto categoryDto1;

    List<Category> allCategoryList;



    @BeforeEach
    void setUp() {

        //modelMapper = mock(ModelMapper.class);

        autoCloseable = MockitoAnnotations.openMocks(this); // To Enable all the Mockito Annotations

        //catServ = new CategoryServiceImpl();
        category = new Category(1, "Java", "All Java Questions", null);
        category1 = new Category(1, "JPA", "All Java Questions", null);
        categoryDto = new CategoryDto(1, "Java", "All Java Questions");
        categoryDto1 = this.modelMapper.map(category1, CategoryDto.class);

        allCategoryList = new ArrayList<>(Arrays.asList(category, category1));
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
        Mockito.reset(catRepo); // to reset the Mock Object which is created using @Mock annotation
    }

    @Test
    void testAllCategories() {
        when(catRepo.findAll()).thenReturn(allCategoryList);
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);

        assertThat(this.catServ.allCategories().get(0).getCategoryTitle()).isEqualTo(allCategoryList.get(0).getCategoryTitle()); // Java
        assertThat(this.catServ.allCategories().get(0).getCategoryDescription()).isEqualTo(allCategoryList.get(0).getCategoryDescription()); // All Java Questions
    }

    @Test
    void testCategoryById() {
        when(catRepo.findById(1)).thenReturn(Optional.of(allCategoryList.get(0)));
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);

        System.out.println("Actual - " + this.catServ.categoryById(1).getCategoryId());
        System.out.println("Expected - " + this.allCategoryList.get(0).getCategoryId());
        assertThat(this.catServ.categoryById(1).getCategoryId()).isEqualTo(this.allCategoryList.get(0).getCategoryId());
        assertThat(this.catServ.categoryById(1).getCategoryTitle()).isEqualTo(this.allCategoryList.get(0).getCategoryTitle());

    }

    @Test
    void testNewCategory() {
        //mock(CategoryRepository.class);
        //mock(Category.class);

        when(catRepo.save(category)).thenReturn(category);
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);

        assertThat(catServ.newCategory(categoryDto).getCategoryId()).isEqualTo(category.getCategoryId());
    }
}
