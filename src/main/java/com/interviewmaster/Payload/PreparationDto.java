package com.interviewmaster.Payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class PreparationDto {

    private int id;

    @NotEmpty(message = "Question field should not be empty!!")
    private String question;

    @NotEmpty(message = "Answer field should not be empty!!")
    private String answer;

    @NotEmpty(message = "Category field should not be empty!!")
    private String category;

    private boolean correct;
}
