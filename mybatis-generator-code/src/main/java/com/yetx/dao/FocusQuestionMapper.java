package com.yetx.dao;

import com.yetx.pojo.FocusQuestion;

public interface FocusQuestionMapper {
    int deleteByPrimaryKey(String id);

    int insert(FocusQuestion record);

    int insertSelective(FocusQuestion record);

    FocusQuestion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FocusQuestion record);

    int updateByPrimaryKey(FocusQuestion record);
}