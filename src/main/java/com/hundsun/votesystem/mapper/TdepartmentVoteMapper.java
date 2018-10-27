package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.TdepartmentVote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TdepartmentVoteMapper {
    int deleteByPrimaryKey(Integer Id);

    int insert(TdepartmentVote record);

    int insertWithOutId(@Param("dvDiid") int departmentId, @Param("dvViid")  int voteInfoId);

    int insertSelective(TdepartmentVote record);

    TdepartmentVote selectByPrimaryKey(Integer Id);

    int updateByPrimaryKeySelective(TdepartmentVote record);

    int updateByPrimaryKey(TdepartmentVote record);
}