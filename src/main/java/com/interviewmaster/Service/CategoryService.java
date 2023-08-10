package com.interviewmaster.Service;

import com.interviewmaster.Payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> allCategories();

    CategoryDto categoryById(int categoryId);

    CategoryDto newCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(int categoryId, CategoryDto categoryDto);

    void deleteCategory(int categoryId);
}
