package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.TdepartmentVote;

public interface TdepartmentVoteMapper {
    int deleteByPrimaryKey(Integer Id);

    int insert(TdepartmentVote record);

    int insertSelective(TdepartmentVote record);

    TdepartmentVote selectByPrimaryKey(Integer Id);

    int updateByPrimaryKeySelective(TdepartmentVote record);

    int updateByPrimaryKey(TdepartmentVote record);
}