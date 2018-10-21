package com.hundsun.votesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
//   查询员工是否有投票资格
    int select(@Param("svSiid") int staffId,@Param("svViid")  int voteInfoId);
    //    查询是否投过票
    int selectIsVoted(@Param("svSiid") int staffId,@Param("svViid")  int voteInfoId);

    void changeVoteStatus(Map<String,Object> param);
}