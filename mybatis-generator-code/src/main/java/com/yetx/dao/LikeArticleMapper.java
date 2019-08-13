package com.yetx.dao;

import com.yetx.pojo.LikeArticle;

public interface LikeArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(LikeArticle record);

    int insertSelective(LikeArticle record);

    LikeArticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LikeArticle record);

    int updateByPrimaryKey(LikeArticle record);
}