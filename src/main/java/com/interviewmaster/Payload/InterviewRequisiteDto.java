package com.interviewmaster.Payload;

import com.interviewmaster.Model.Category;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class InterviewRequisiteDto {

    private int id;


    private String interviewerName;


    private String companyName;

    private List<Integer> selectedTopics;


    private int questionCount;


    private int time;
}
