package com.interviewmaster.Payload;

import com.interviewmaster.Model.Preparation;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private int categoryId;

    private String categoryTitle;

    private String categoryDescription;

    private List<Preparation> preparation;
}
