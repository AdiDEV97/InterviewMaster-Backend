package com.interviewmaster.Payload;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.InterviewRequisite;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class PreparationDto {

    private int id;

    @NotEmpty(message = "Question field should not be empty!!")
    private String question;

    @NotEmpty(message = "Answer field should not be empty!!")
    private String answer;

    private String correct;

    private Category category;
}
