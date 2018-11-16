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
    @Autowired
    private StaffVoteDetailInfoMapper staffVoteDetailInfoMapper;

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
        String voteCreaterName=staffInfoMapper.selectByPrimaryKey(voteInfo.getVoteCreaterId()).getSiName();
        voteInfoWithStaffNum.setVoteCreaterName(voteCreaterName);
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

        //我参与的投票
        List<Integer> voteIdList=staffVoteDetailInfoMapper.selectVoteInfoIdByPartId(staffId);
        Set<Integer> voteIdSet=new HashSet<>();
        //去重
        for(Integer i:voteIdList)
            voteIdSet.add(i);
        List<VoteInfoWithStaffNum> voteInfoWithStaffNumsParticipant=new ArrayList<>();
        for(int i:voteIdSet){
           VoteInfo vo=voteInfoMapper.selectByPrimaryKey(i);
           VoteInfoWithStaffNum voteInfoWithStaffNum=setStaffNum(vo);
           voteInfoWithStaffNumsParticipant.add(voteInfoWithStaffNum);
        }
        voteInfoMap.put(VoteConsant.VOTE_BY_PARTICIPANT,voteInfoWithStaffNumsParticipant);


       //我参与的 我创建的中已经结束的投票
       List<VoteInfoWithStaffNum> voteInfoWithStaffNumsEnd=new ArrayList<>();
      for(VoteInfoWithStaffNum v:voteInfoWithStaffNums){
          if(v.getVoteStatus()==2)
              voteInfoWithStaffNumsEnd.add(v);
      }
       for(VoteInfoWithStaffNum v:voteInfoWithStaffNumsParticipant){
           if(v.getVoteStatus()==2)
               voteInfoWithStaffNumsEnd.add(v);
       }

       voteInfoMap.put(VoteConsant.VOTE_END,voteInfoWithStaffNumsEnd);
        return voteInfoMap;
   }

    @Override
    public int createVote(VoteInfo voteInfo,List<Integer> stafflist,List<String> voteOptionList,int departmentId,int voteAuthorityType) {
        int result=1;
        result= voteInfoMapper.insert(voteInfo);
        int voteInfoId=voteInfo.getVoteId();
        if(voteAuthorityType==VoteConsant.VOTE_ATHORITY_TYPE_STAFF){
            for(int staffId:stafflist)
                tstaffVoteMapper.insert(staffId,voteInfoId,0);
        }
        if(voteAuthorityType==VoteConsant.VOTE_ATHORITY_TYPE_DEPARTMENT){

        }

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
    	
		//System.out.println("当前更新投票ID：  "+voteInfo.getVoteId());
		 Date now = new Date();
	        try {
	            Date createtime = voteInfo.getVoteBeginTime();
	            Date endtime = voteInfo.getVoteEndTime();
	            //System.out.println(now.getTime());
	            if (now.getTime() >= endtime.getTime()) {
	                //System.out.println("------投票已经结束------");
	                voteInfoMapper.updateStatus(voteInfo.getVoteId(), 2);
	            } else if (now.getTime() >= createtime.getTime() && now.getTime() < endtime.getTime()) {
	                //System.out.println("------投票正在进行------");
	                voteInfoMapper.updateStatus(voteInfo.getVoteId(), 1);
	            } else if (now.getTime() < createtime.getTime() && now.getTime() < endtime.getTime()){
	            	//System.out.println("------投票还未开始------");
	                voteInfoMapper.updateStatus(voteInfo.getVoteId(), 0);
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

	@Override
	public List<VoteInfo> selectAllVoteInfo() {
		return voteInfoMapper.selectAllVoteInfo();
		 
	}
}
