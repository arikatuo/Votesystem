package com.hundsun.votesystem.service.impl;

import com.google.gson.Gson;
import com.hundsun.votesystem.mapper.*;
import com.hundsun.votesystem.model.*;
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
    @Autowired
    private  VoteInfoMapper voteInfoMapper;
    @Autowired
    private VoteOptionMapper voteOptionMapper;
    @Autowired
    private TdepartmentVoteMapper tdepartmentVoteMapper;
    @Autowired
    private StaffInfoMapper staffInfoMapper;

    @Override
    public void vote(Map<String,Object> param){
        staffVoteDetailInfoMapper.insertVoteInfo(param);
        tstaffVoteMapper.changeVoteStatus(param);
        if (voteSituationInfoMapper.select(param)==0){
            voteSituationInfoMapper.insert(param);
        }
        else{
            voteSituationInfoMapper.updateVoteNum(param);
        }
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

    @Override
    public VoteInfo selectByPrimaryKey( int voteId) {
        return voteInfoMapper.selectByPrimaryKey(voteId);
    }

    @Override
    public List<Map<String,Object>> selectOptionInfo(int voteId){
        return voteOptionMapper.selectOptionInfo(voteId);
    }

    @Override
    public  Map<String,Object> selectVoteInfo(int voteId){
        return voteInfoMapper.selectVoteInfo(voteId);
    }

    @Override
    public List<Integer> selectDepartmentId(int voteId){
        return tdepartmentVoteMapper.selectDepartmentId(voteId);
    }

    @Override
    public int selectByStaffId(int staffId){
        return staffInfoMapper.selectByStaffId(staffId);
    }

}
