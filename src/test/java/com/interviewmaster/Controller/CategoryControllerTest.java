package com.interviewmaster.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewmaster.Model.Category;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Service.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // get() method
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
        category2 = new Category(2, "JPA", "All JPA Questions", null);
        categoryDto1 = new CategoryDto(1, "Java", "All Java Questions");
        categoryDto2 = new CategoryDto(2, "JPA", "All JPA Questions");

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


    @Test
    public void testGetCategoryById() throws Exception {
        when(this.catServ.categoryById(1)).thenReturn(categoryDto1);

        this.mockMvc.perform(get("/api/v1/category/id-1")).andDo(print()).andExpect(jsonPath("$.categoryId").value(1));
        this.mockMvc.perform(get("/api/v1/category/id-1")).andExpect(jsonPath("$.categoryTitle").value("Java"));
        this.mockMvc.perform(get("/api/v1/category/id-1")).andExpect(jsonPath("$.categoryDescription").value("All Java Questions"));
        this.mockMvc.perform(get("/api/v1/category/id-1")).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddNewCategory() throws Exception {
        when(this.catServ.newCategory(categoryDto1)).thenReturn(categoryDto1);

        // Convert Object into Json String (Object)
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(categoryDto1);
        System.out.println("--------> JSON String - " + jsonString);

        // Here we add ".contentType(MediaType.APPLICATION_JSON).content(jsonString)" --> because AddNewCategory() method and UpdateCategory() method takes data from the user.
        this.mockMvc.perform(post("/api/v1/category/new-category").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(post("/api/v1/category/new-category").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(jsonPath("$.categoryId").value(1));
        this.mockMvc.perform(post("/api/v1/category/new-category").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(jsonPath("$.categoryTitle").value("Java"));
        this.mockMvc.perform(post("/api/v1/category/new-category").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(jsonPath("$.categoryDescription").value("All Java Questions"));
    }


    @Test
    public void testUpdateCategory() throws Exception {
        when(this.catServ.updateCategory(1, categoryDto2)).thenReturn(categoryDto2);

        // Convert Object into Json String (Object)
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(categoryDto2);

        System.out.println("----------> Category2 before update - " + category1);

        // Here we add ".contentType(MediaType.APPLICATION_JSON).content(jsonString)" --> because AddNewCategory() method and UpdateCategory() method takes data from the user.
        this.mockMvc.perform(put("/api/v1/category/update/id-1").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(put("/api/v1/category/update/id-1").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andDo(print()).andExpect(jsonPath("$.categoryId").value(2));
        this.mockMvc.perform(put("/api/v1/category/update/id-1").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(jsonPath("$.categoryTitle").value("JPA"));
        this.mockMvc.perform(put("/api/v1/category/update/id-1").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(jsonPath("$.categoryDescription").value("All JPA Questions"));
    }


    @Test
    public void testDeleteCategory() throws Exception {
        doNothing().when(this.catServ).deleteCategory(1);
        this.mockMvc.perform(delete("/api/v1/category/delete/id-1")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(delete("/api/v1/category/delete/id-1")).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(delete("/api/v1/category/delete/id-1")).andDo(print()).andExpect(jsonPath("$.message").value("The category with id - 1 is deleted successfully"));
        this.mockMvc.perform(delete("/api/v1/category/delete/id-1")).andDo(print()).andExpect(jsonPath("$.status").value(true));
    }


}
