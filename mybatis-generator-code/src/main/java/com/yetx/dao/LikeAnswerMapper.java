package com.yetx.dao;

import com.yetx.pojo.LikeAnswer;

public interface LikeAnswerMapper {
    int deleteByPrimaryKey(String id);

    int insert(LikeAnswer record);

    int insertSelective(LikeAnswer record);

    LikeAnswer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LikeAnswer record);

    int updateByPrimaryKey(LikeAnswer record);
}