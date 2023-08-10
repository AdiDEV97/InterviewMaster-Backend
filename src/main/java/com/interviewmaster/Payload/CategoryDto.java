package com.interviewmaster.Payload;

import com.interviewmaster.Model.Preparation;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CategoryDto {

    private int categoryId;

    @NotEmpty(message = "Please specify the category title!!")
    @Size(min=5, max=50, message = "Description should be between 5 to 50 characters!!")
    private String categoryTitle;

    @NotEmpty(message = "Please specify the category description!!")
    @Size(min=5, max=500, message = "Description should be between 5 to 500 characters!!")
    private String categoryDescription;

    //private List<Preparation> preparation;
}
