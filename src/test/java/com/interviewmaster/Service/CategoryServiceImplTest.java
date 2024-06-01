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
    CategoryDto categoryDto;



    @BeforeEach
    void setUp() {

        //modelMapper = mock(ModelMapper.class);

        autoCloseable = MockitoAnnotations.openMocks(this); // To Enable all the Mockito Annotations

        //catServ = new CategoryServiceImpl();
        category = new Category(1, "Java", "All Java Questions", null);
        categoryDto = new CategoryDto(1, "Java", "All Java Questions");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
        Mockito.reset(catRepo); // to reset the Mock Object which is created using @Mock annotation
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
