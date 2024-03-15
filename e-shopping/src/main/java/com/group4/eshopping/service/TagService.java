package com.group4.eshopping.service;


import com.group4.eshopping.entity.Tag;

import java.util.List;

public interface TagService {
    int getIdByTag(String tag);

    boolean isTagExist(String tag);

    String getTagNameByTagId(int id);

    Tag getTagByTagId(int id);

    Tag getTagByTagName(String tagName);

    int transferTagToTagId(String tag);

    List<Integer> transferTagToTagId(List<String> tags);
}
