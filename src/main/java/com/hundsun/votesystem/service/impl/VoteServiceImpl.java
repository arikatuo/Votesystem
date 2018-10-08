package com.hundsun.votesystem.service.impl;

import com.hundsun.votesystem.mapper.StaffInfoMapper;
import com.hundsun.votesystem.mapper.TstaffVoteMapper;
import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.mapper.VoteOptionMapper;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.VoteOption;
import com.hundsun.votesystem.service.VoteServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteServiceBase {

    @Autowired
    private StaffInfoMapper staffInfoMapper;
    @Autowired
    private VoteInfoMapper voteInfoMapper;
    @Autowired
    private TstaffVoteMapper tstaffVoteMapper;
    @Autowired
    private VoteOptionMapper voteOptionMapper;

    @Override
    public List<StaffInfo> getStaffInfoById(int staffid) {
        List<StaffInfo> staffInfos=new ArrayList<>();
        StaffInfo staffInfo= staffInfoMapper.selectByPrimaryKey(staffid);

        staffInfos.add(staffInfo);
        return staffInfos;
    }

    @Override
    public List<StaffInfo> getStaffInfoByStaffNum(String staffNum) {
        List<StaffInfo> staffInfos=new ArrayList<>();
        StaffInfo staffInfo= staffInfoMapper.selectByStaffNum(staffNum);
        staffInfos.add(staffInfo);
        return staffInfos;
    }

    @Override
    public List<StaffInfo> getStaffInfoByDepId(int depId) {
        return staffInfoMapper.selectByDepId(depId);
    }

    @Override
    public List<StaffInfo> getAllStaff() {
        return staffInfoMapper.selectAll();

    }

    @Override
    public int createVote(VoteInfo voteInfo,List<Integer> stafflist,List<String> voteOptionList) {
        int result=1;
        result= voteInfoMapper.insert(voteInfo);
        int voteInfoId=voteInfo.getVoteId();

        //效率低，可改一次性插入多条数据
        for(int staffId:stafflist)
            tstaffVoteMapper.insert(staffId,voteInfoId);

        //效率低，可改一次性插入多条数据
        for(String voteOptionName:voteOptionList){
            VoteOption vo=new VoteOption();
            vo.setVoteOptionName(voteOptionName);
            vo.setVoteInfoId(voteInfoId);
            voteOptionMapper.insert(vo);
            voteInfo.setVoteOptionList(vo);
        }
        return result;
    }
}
