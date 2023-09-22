package com.interviewmaster.Dao;

import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import org.hibernate.type.IntegerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PreparationRepo extends JpaRepository<Preparation, Integer> {
    List<Preparation> findByCategory(Category category);

    @Query("select prep from Preparation prep where prep.question like :key")
    List<Preparation> searchByQuestion(@Param("key") String keyword);

    /*@Query("select prep from Preparation prep where prep.question like %?1%")
    List<Preparation> findPreparationByQuestionLike(@Param("key") String keyword);*/

    @Query("SELECT p FROM Preparation p WHERE p.category in (:categories)")
    List<Preparation> findByCategories(@Param("categories") List<Category> categories);
}
