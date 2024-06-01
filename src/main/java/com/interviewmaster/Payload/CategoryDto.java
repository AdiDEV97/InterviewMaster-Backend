package com.interviewmaster.Payload;

import com.interviewmaster.Model.Preparation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;

    @NotEmpty(message = "Please specify the category title!!")
    @Size(min=2, max=50, message = "Title should be between 2 to 50 characters!!")
    private String categoryTitle;

    @NotEmpty(message = "Please specify the category description!!")
    @Size(min=3 , max=1000, message = "Description should be between 5 to 1000 characters!!")

    private String categoryDescription;

    //private List<Preparation> preparation;
}
