package com.hundsun.votesystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.hundsun.votesystem.model.TstaffVote;

@Mapper
@Component
public interface TstaffVoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("svSiid") int staffInfoId,@Param("svViid")  int voteInfoId);
    
    
    int delete(@Param("svSiid") int staffInfoId,@Param("svViid")  int voteInfoId);
    //根据投票id查询投票和投票人信息
    List<TstaffVote> selectByVoteId(@Param("voteId")Integer voteId);
    int deleteByVoteId(@Param("svViid")Integer svViid);

//    int insertSelective(TstaffVote record);
//

//    int updateByPrimaryKeySelective(TstaffVote record);
//
//    int updateByPrimaryKey(TstaffVote record);
}