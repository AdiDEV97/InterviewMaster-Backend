package com.interviewmaster.ServiceTest;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Service.CategoryService;
import com.interviewmaster.Service.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceTest  {

    @Autowired
    private Logger logger;

    private CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();

    @Test
    public void CategoryServiceTest() {
        //CategoryServiceImpl cs = new CategoryServiceImpl()
        //List<CategoryDto> actualList = new CategoryServiceImpl().allCategories();
        System.out.println("Test Working...");
    }
}
