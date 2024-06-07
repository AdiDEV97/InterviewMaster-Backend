package com.interviewmaster.Payload;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.InterviewRequisite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewRequisiteDto {

    //private int id;


    @NotEmpty(message = "Interviewer Name should not be Empty!!")
    private String interviewerName;


    @NotEmpty(message = "Company name should not be Empty!!")
    private String companyName;

    @NotEmpty(message = "Select at-least one topic!!")
    private List<Integer> selectedTopics;


    @NotNull
    private int questionCount;


    @NotNull
    private int time;

    private List<PreparationDto> preparationDto;
}
