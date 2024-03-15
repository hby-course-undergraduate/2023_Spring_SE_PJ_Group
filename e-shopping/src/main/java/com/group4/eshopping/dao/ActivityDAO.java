package com.group4.eshopping.dao;

import com.group4.eshopping.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityDAO extends JpaRepository<Activity, Integer> {
    Activity findById(int id);

    @Query("select activity from Activity activity where activity.state = 1")
    List<Activity> findValidActivity();
}
