package com.interviewmaster.Dao;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
