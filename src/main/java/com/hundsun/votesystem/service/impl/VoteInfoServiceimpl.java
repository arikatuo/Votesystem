package com.hundsun.votesystem.service.impl;

import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sumimasah on 2018/10.
 */
import org.springframework.stereotype.Service;
@Service
public class VoteInfoServiceimpl implements VoteInfoService {
    @Autowired
    private VoteInfoMapper voteInfoMapper;

    @Override
    public VoteInfo selectByPrimaryKey(Integer voteId) {
        VoteInfo voteInfo=voteInfoMapper.selectByPrimaryKey(voteId);
        return voteInfo;
    }

    @Override
    public List<HashMap<Integer,Integer>> getVoterNum(Integer voteInfoId) {
        List<HashMap<Integer,Integer>> voteNum=voteInfoMapper.getVoterNum(voteInfoId);
        return voteNum;
    }

    @Override
    public List<HashMap<Integer,Integer>> getVoteOptionNum(Integer voteId){
        List<HashMap<Integer,Integer>> list=voteInfoMapper.getVoteOptionNum(voteId);
        return list;

    }

    

}
