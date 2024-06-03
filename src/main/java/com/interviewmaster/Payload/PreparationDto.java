package com.interviewmaster.Payload;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.InterviewRequisite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparationDto {

    private int id;

    @NotEmpty(message = "Question field should not be empty!!")
    private String question;

    @NotEmpty(message = "Answer field should not be empty!!")
    private String answer;

    private String correct;

    private Category category;
}
