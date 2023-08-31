package com.interviewmaster.Dao;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreparationRepo extends JpaRepository<Preparation, Integer> {
    List<Preparation> findByCategory(Category category);

    @Query("select prep from Preparation prep where prep.question like :key")
    List<Preparation> searchByQuestion(@Param("key") String keyword);
}
