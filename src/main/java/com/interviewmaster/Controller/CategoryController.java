package com.interviewmaster.Controller;

import com.interviewmaster.Payload.ApiResponse;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = {"http://localhost:3000", "http://16.170.82.157:5001"})
public class CategoryController {

    @Autowired
    private CategoryService catServ;

    @GetMapping("/all-categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> allCategories = this.catServ.allCategories();
        if(allCategories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(allCategories));
    }

    @GetMapping("/id-{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@Valid @PathVariable("categoryId") int categoryId) {
        CategoryDto categoryById = this.catServ.categoryById(categoryId);
        if(categoryById == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(categoryById));
    }


    @PostMapping("/new-category")
    public ResponseEntity<CategoryDto> addNewCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto saveCategory = this.catServ.newCategory(categoryDto);
        return ResponseEntity.of(Optional.of(saveCategory));
    }


    @PutMapping("/update/id-{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable("categoryId") int categoryId, @RequestBody CategoryDto categoryDto) {
        CategoryDto updateCategory = this.catServ.updateCategory(categoryId, categoryDto);
        return ResponseEntity.of(Optional.of(updateCategory));
    }


    @DeleteMapping("/delete/id-{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@Valid @PathVariable("categoryId") int categoryId) {
        this.catServ.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(String.format("The category with id - %s is deleted successfully", categoryId), true), HttpStatus.OK);
    }
}
