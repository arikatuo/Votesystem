package com.hundsun.votesystem.service;

import com.hundsun.votesystem.model.VoteInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
/**
 * Created by sumimasah on 2018/10.
 */
@Service
public interface VoteInfoService {
    //获取投票基本信息
    VoteInfo selectByPrimaryKey(Integer voteId);

    //获取投票参与人数
    List<HashMap<Integer,Integer>> getVoterNum(Integer voteInfoId);

    List<HashMap<Integer,Integer>> getVoteOptionNum(Integer voteId);
    
}
