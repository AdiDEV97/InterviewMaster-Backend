package com.interviewmaster.Controller;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Service.Impl.CategoryServiceImpl;
import org.hibernate.criterion.Example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // get() method
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class) // Load Application Context ---> For which class you want to write test cases
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl catServ;

    Category category1;
    Category category2;

    CategoryDto categoryDto1;
    CategoryDto categoryDto2;

    List<CategoryDto> categoryList;

    @BeforeEach
    public void setUp() {
        category1 = new Category(1, "Java", "All Java Questions", null);
        category2 = new Category(1, "JPA", "All JPA Questions", null);
        categoryDto1 = new CategoryDto(1, "Java", "All Java Questions");
        categoryDto2 = new CategoryDto(1, "JPA", "All JPA Questions");

        categoryList = new ArrayList<>(Arrays.asList(categoryDto1, categoryDto2));
    }


    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testGetAllCategories() throws Exception {
        when(this.catServ.allCategories()).thenReturn(categoryList);

        this.mockMvc.perform(get("/api/v1/category/all-categories")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/api/v1/category/all-categories")).andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/api/v1/category/all-categories")).andExpect(jsonPath("$").isArray());
    }


}
