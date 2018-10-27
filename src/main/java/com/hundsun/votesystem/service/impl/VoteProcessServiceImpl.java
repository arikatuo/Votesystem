package com.hundsun.votesystem.service.impl;

import com.google.gson.Gson;
import com.hundsun.votesystem.mapper.*;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.VoteOption;
import com.hundsun.votesystem.model.returndata.VoteInfoWithStaffNum;
import com.hundsun.votesystem.service.IVoteProcessService;
import com.hundsun.votesystem.service.VoteServiceBase;
import com.hundsun.votesystem.util.VoteConsant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VoteProcessServiceImpl implements IVoteProcessService {

    @Autowired
    private  StaffVoteDetailInfoMapper staffVoteDetailInfoMapper;
    @Autowired
    private TstaffVoteMapper tstaffVoteMapper;
    @Autowired
    private VoteSituationInfoMapper voteSituationInfoMapper;

    @Override
    public String vote(Map<String,Object> param){
        ReturnData returnData=new ReturnData();

        staffVoteDetailInfoMapper.insertVoteInfo(param);
        tstaffVoteMapper.changeVoteStatus(param);
        if (voteSituationInfoMapper.select(param)==0){
            voteSituationInfoMapper.insert(param);
        }
        else{
            voteSituationInfoMapper.updateVoteNum(param);
        }


        returnData.setReturnMsg("sucess！");
        return new Gson().toJson(returnData);

    }
    @Override
    public boolean isInStafflist(int staffId, int voteInfoId) {
        boolean IsInStafflistFlag = false;
        if(tstaffVoteMapper.select(staffId,voteInfoId)==1){
            IsInStafflistFlag = true;
        }
        return IsInStafflistFlag;
    }
    @Override
    public boolean isVoted(int staffId, int voteInfoId){
        boolean IsVotedFlag = false;
        if(tstaffVoteMapper.selectIsVoted(staffId,voteInfoId)==1){
            IsVotedFlag = true;
        }
        return IsVotedFlag;
    }
}
