package com.hundsun.votesystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import com.hundsun.votesystem.model.TstaffVote;

import java.util.List;
import java.util.Map;


@Mapper
@Component
public interface TstaffVoteMapper {
    List<Integer> getVoteInfoIdList(int staffId);
    int deleteByPrimaryKey(Integer id);

    //根据投票id查询投票和投票人信息
    List<TstaffVote> selectByVoteId(@Param("voteId")Integer voteId);
    int deleteByVoteId(@Param("svViid")Integer svViid);

//    int insertSelective(TstaffVote record);
//

    //    int updateByPrimaryKeySelective(TstaffVote record);
//
//    int updateByPrimaryKey(TstaffVote record);
//   查询员工是否有投票资格
    int select(@Param("svSiid") int staffId,@Param("svViid")  int voteInfoId);
    //    查询是否投过票
    int selectIsVoted(@Param("svSiid") int staffId,@Param("svViid")  int voteInfoId);

    void changeVoteStatus(Map<String,Object> param);

    //新增投票和投票员工信息
    int insert(@Param("svSiid") int staffInfoId, @Param("svViid")int voteInfoId, @Param("isVoted")int isVoted);
    //根据投票ID和员工ID删除记录

    int delete(@Param("svSiid") int staffInfoId,@Param("svViid")int voteInfoId);



}