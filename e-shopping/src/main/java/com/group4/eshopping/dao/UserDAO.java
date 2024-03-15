package com.group4.eshopping.dao;

import com.group4.eshopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByIdCard(String idCard);

    User findById(int id);

    User findByPhone(String phone);

    User findByEmail(String email);

    User getByUsernameAndPassword(String username, String password);
}

