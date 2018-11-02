package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.returndata.VoteOptionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Mapper
@Component
public interface VoteInfoMapper {

    List<VoteInfo> selectVoteInfoListBycreterId(Integer createrId);

    List<VoteInfo> selectVoteInfoListByparticipateId(Integer staffId);

    List<VoteInfo> selectEndVoteInfoList();

    int deleteByPrimaryKey(Integer voteId);

    int insert(VoteInfo record);

    int insertSelective(VoteInfo record);

    VoteInfo selectByPrimaryKey(Integer voteId);

    int updateByPrimaryKeySelective(VoteInfo record);

    int updateByPrimaryKey(VoteInfo record);

    //获取投票参与人数
    List<HashMap<String,Integer>> getVoterNum(Integer voteId);

    //获取选项投票情况
    List<VoteOptionInfo> getVoteOptionNum(Integer voteId);
    
    //更新数据库投票状态
    void updateStatus(@Param("voteId")Integer voteId, @Param("voteStatus")Integer voteStatus);
}