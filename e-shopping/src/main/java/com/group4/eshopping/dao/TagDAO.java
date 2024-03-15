package com.group4.eshopping.dao;

import com.group4.eshopping.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDAO extends JpaRepository<Tag, Integer> {
    Tag findByTagId(int tagId);

    Tag findByTagName(String tagName);
}
