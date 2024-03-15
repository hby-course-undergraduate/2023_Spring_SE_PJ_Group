package com.group4.eshopping.service.impl;

import com.group4.eshopping.dao.TagDAO;
import com.group4.eshopping.entity.Tag;
import com.group4.eshopping.exception.CheckException;
import com.group4.eshopping.service.TagService;
import com.group4.eshopping.utils.MyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDAO tagDAO;

    @Override
    public int getIdByTag(String tagName) {
        return tagDAO.findByTagName(tagName).getTagId();
    }

    @Override
    public boolean isTagExist(String tagName) {
        return tagDAO.findByTagName(tagName) != null;
    }

    @Override
    public String getTagNameByTagId(int id) {
        return tagDAO.findByTagId(id).getTagName();
    }

    @Override
    public Tag getTagByTagId(int id) {
        return tagDAO.findByTagId(id);
    }

    @Override
    public Tag getTagByTagName(String tagName) {
        return tagDAO.findByTagName(tagName);
    }

    public void addTag(String tagName) {
        if (getTagByTagName(tagName) == null) {
            Tag tag = new Tag(tagName);
            tagDAO.save(tag);
        }
    }

    //    单个tag
    @Override
    public int transferTagToTagId(String tag) {
        Tag tag1 = tagDAO.findByTagName(tag);
        if (tag1 == null)
            throw new CheckException(HttpServletResponse.SC_NOT_FOUND, "无对应tag");
        return tag1.getTagId();
    }

    //    已分割
    @Override
    public List<Integer> transferTagToTagId(List<String> tags) {
        List<Integer> tagId = new ArrayList<>();
        for (String tag : tags) {
            tagId.add(transferTagToTagId(tag));
        }
        return tagId;
    }

    //    未分割
    public List<Integer> transferTagsToTagId(String tags) {
        String[] list = tags.split(",");
        List<Integer> tagId = new ArrayList<>();
        for (String tag : list) {
            tagId.add(transferTagToTagId(tag));
        }
        return tagId;
    }

}
