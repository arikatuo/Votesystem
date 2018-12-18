package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.TdepartmentInfo;

public interface TdepartmentInfoMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int insert(TdepartmentInfo record);

    int insertSelective(TdepartmentInfo record);

    TdepartmentInfo selectByPrimaryKey(Integer departmentId);

    int updateByPrimaryKeySelective(TdepartmentInfo record);

    int updateByPrimaryKey(TdepartmentInfo record);
}