package com.interviewmaster.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "categoryTitle")
    private String categoryTitle;

    @Column(name = "categoryDescription", length = 1000)
    @Size(min = 3, max = 1000, message = "Write atleast 3 characters!!")
    private String categoryDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Preparation> preparation = new ArrayList<>();


}
