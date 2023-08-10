package com.interviewmaster.Service.Impl;

import com.interviewmaster.Dao.CategoryRepository;
import com.interviewmaster.Exceptions.ResourceNotFoundException;
import com.interviewmaster.Model.Category;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository catRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> allCategories() {
        List<Category> getAllCategories = this.catRepo.findAll();
        List<CategoryDto> getAllCategoriesDto = getAllCategories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return getAllCategoriesDto;
    }

    @Override
    public CategoryDto categoryById(int categoryId) {
        Category getCategoryById = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        return this.modelMapper.map(getCategoryById, CategoryDto.class);
    }

    @Override
    public CategoryDto newCategory(CategoryDto categoryDto) {
        Category addNewCategory = this.modelMapper.map(categoryDto, Category.class);
        Category saveCategory = this.catRepo.save(addNewCategory);
        return this.modelMapper.map(saveCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(int categoryId, CategoryDto categoryDto) {
        Category category = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category save = this.catRepo.save(category);
        return this.modelMapper.map(save, CategoryDto.class);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        this.catRepo.delete(category);
    }
}
