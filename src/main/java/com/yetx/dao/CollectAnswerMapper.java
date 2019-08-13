package com.yetx.dao;

import com.yetx.pojo.CollectAnswer;

public interface CollectAnswerMapper {
    int deleteByPrimaryKey(String id);

    int insert(CollectAnswer record);

    int insertSelective(CollectAnswer record);

    CollectAnswer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CollectAnswer record);

    int updateByPrimaryKey(CollectAnswer record);
}