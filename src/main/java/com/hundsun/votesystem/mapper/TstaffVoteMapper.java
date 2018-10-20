package com.hundsun.votesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TstaffVoteMapper {
    List<Integer> getVoteInfoIdList(int staffId);
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("svSiid") int staffInfoId,@Param("svViid")  int voteInfoId);

//    int insertSelective(TstaffVote record);
//
//    TstaffVote selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(TstaffVote record);
//
//    int updateByPrimaryKey(TstaffVote record);
}