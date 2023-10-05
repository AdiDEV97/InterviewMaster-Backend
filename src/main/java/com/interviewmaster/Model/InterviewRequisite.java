package com.interviewmaster.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


//@Entity
@Data
public class InterviewRequisite {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    //private int id;

    //@Column(name = "interviewerName")
    private String interviewerName;

    //@Column(name = "companyName")
    private String companyName;

    //@ElementCollection
    private List<Category> selectedTopics;

    //@Column(name = "questionCount")
    private int questionCount;

    //@Column(name = "time")
    private int time;
}
