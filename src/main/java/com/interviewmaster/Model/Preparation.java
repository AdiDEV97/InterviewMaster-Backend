package com.interviewmaster.Model;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;

@Entity
@Data
public class Preparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "questions")
    private String question;

    @Column(name = "answers")
    private String answer;

    @Column(name = "category")
    private String category;

    @Column(name = "isCorrect")
    private boolean isCorrent = false;
}
