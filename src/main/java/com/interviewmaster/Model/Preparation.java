package com.interviewmaster.Model;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Preparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "questions")
    private String question;

    @Column(name = "answers", length = 1000000000)
    @Size(min = 3, max = 1000000000, message = "Write atleast 3 characters!!")
    private String answer;

    @Column(name = "category")
    private String category;

    @Column(name = "isCorrect")
    private boolean correct;
}
