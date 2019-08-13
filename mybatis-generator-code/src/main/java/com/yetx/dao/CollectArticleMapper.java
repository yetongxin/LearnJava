package com.yetx.dao;

import com.yetx.pojo.CollectArticle;

public interface CollectArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(CollectArticle record);

    int insertSelective(CollectArticle record);

    CollectArticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CollectArticle record);

    int updateByPrimaryKey(CollectArticle record);
}