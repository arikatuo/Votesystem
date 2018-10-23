package com.hundsun.votesystem.service.impl;

import com.hundsun.votesystem.mapper.*;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.VoteOption;
import com.hundsun.votesystem.model.returndata.VoteInfoWithStaffNum;
import com.hundsun.votesystem.service.VoteServiceBase;
import com.hundsun.votesystem.util.VoteConsant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;

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
    @Autowired
	private VoteOperationMapper voteOperationMapper;

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

    /**
     * 讲voteinfo转化为VoteInfoWithStaffNum
     * @param voteInfo
     * @return
     */
    private VoteInfoWithStaffNum setStaffNum(VoteInfo voteInfo){
        int id=voteInfo.getVoteId();
        VoteInfoWithStaffNum voteInfoWithStaffNum= new VoteInfoWithStaffNum(voteInfo);
        List<HashMap<String,Integer>> listhashmap=voteInfoMapper.getVoterNum(id);
        int staffNumVoted=0;
        int staffNumNotVoted=0;
        for(HashMap<String,Integer> hashMap:listhashmap) {
            int voteStatus = hashMap.get("voteState");
            String voteNumStr = String.valueOf(hashMap.get("voterNum"));
            int voteNum=Integer.parseInt(voteNumStr);
            if (voteStatus == 0)
                staffNumNotVoted = voteNum;
            if (voteStatus == 1)
                staffNumVoted = voteNum;
        }
        voteInfoWithStaffNum.setStaffNumVoted(staffNumVoted);
        voteInfoWithStaffNum.setStaffNumNotVoted(staffNumNotVoted);
        return voteInfoWithStaffNum;

    }
   @Override
    public Map<String, List<VoteInfoWithStaffNum>> getClassificationVoteInfo(int staffId) {
        Map<String,List<VoteInfoWithStaffNum>> voteInfoMap=new HashMap<>();

        //创建者所有投票
       List<VoteInfoWithStaffNum> voteInfoWithStaffNums=new ArrayList<>();
        List<VoteInfo> voteInfoList= voteInfoMapper.selectVoteInfoListBycreterId(staffId);
        for(VoteInfo vinfo:voteInfoList){
            VoteInfoWithStaffNum voteInfoWithStaffNum=setStaffNum(vinfo);
            voteInfoWithStaffNums.add(voteInfoWithStaffNum);
        }
        voteInfoMap.put(VoteConsant.VOTE_BY_CREATEID,voteInfoWithStaffNums);

        //已结束所有投票
       List<VoteInfo> voteInfoListEnd=voteInfoMapper.selectEndVoteInfoList();
       List<VoteInfoWithStaffNum> voteInfoWithStaffNumsEnd=new ArrayList<>();
       for(VoteInfo vinfo:voteInfoListEnd){
           VoteInfoWithStaffNum voteInfoWithStaffNum=setStaffNum(vinfo);
           voteInfoWithStaffNumsEnd.add(voteInfoWithStaffNum);
       }
       voteInfoMap.put(VoteConsant.VOTE_END,voteInfoWithStaffNumsEnd);

       //参与者所有投票
       List<VoteInfoWithStaffNum> voteInfoWithStaffNumsParticipant=new ArrayList<>();
      for(VoteInfoWithStaffNum v:voteInfoWithStaffNums){
          if(v.getVoteStatus()==2)
              voteInfoWithStaffNumsParticipant.add(v);
      }
       for(VoteInfoWithStaffNum v:voteInfoWithStaffNumsEnd){
           if(v.getVoteStatus()==2)
               voteInfoWithStaffNumsParticipant.add(v);
       }

       voteInfoMap.put(VoteConsant.VOTE_BY_PARTICIPANT,voteInfoWithStaffNumsParticipant);
        return voteInfoMap;
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

    @Override
	public void updateVoteStatus(VoteInfo voteInfo) {
		//VoteInfo vote = voteInfoMapper.selectByPrimaryKey(voteid);
		System.out.println(voteInfo);
		 Date now = new Date();
	        try {
	            Date createtime = voteInfo.getVoteCreateTime();
	            Date endtime = voteInfo.getVoteEndTime();
	            if (now.getTime() < createtime.getTime()) {
	                System.out.println("------现在投票还未开始------");
	                voteInfoMapper.updateStatus(voteInfo.getVoteId(), 0);
	            } else if (now.getTime() >= createtime.getTime() && now.getTime() < endtime.getTime()) {
	                System.out.println("------投票正在进行------");
	                voteInfoMapper.updateStatus(voteInfo.getVoteId(), 1);
	            } else if (now.getTime() >= endtime.getTime()){
	            	System.out.println("------投票已经结束------");
	                voteInfoMapper.updateStatus(voteInfo.getVoteId(), 2);
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        System.out.println("------投票状态已更新------");
		return;

	}

	@Override
	public int deleteVote(int voteInfoId){
		int num=voteOperationMapper.deleteVote(voteInfoId);
    	return num;
	}
}
