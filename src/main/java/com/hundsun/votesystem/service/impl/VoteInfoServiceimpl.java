package com.hundsun.votesystem.service.impl;

import com.hundsun.votesystem.mapper.TdepartmentVoteMapper;
import com.hundsun.votesystem.mapper.TstaffVoteMapper;
import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.TstaffVote;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.returndata.VoteOptionInfo;
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
    @Autowired
    private TstaffVoteMapper staffVoteMapper;
    @Autowired
    private TdepartmentVoteMapper departmentVoteMapper;

    @Override
    public VoteInfo selectByPrimaryKey(Integer voteId) {
        VoteInfo voteInfo=voteInfoMapper.selectByPrimaryKey(voteId);
        return voteInfo;
    }

    @Override
    public List<HashMap<String,Integer>> getVoterNum(Integer voteInfoId) {
        List<HashMap<String,Integer>> voteNum=voteInfoMapper.getVoterNum(voteInfoId);
        return voteNum;
    }

    @Override
    public List<VoteOptionInfo> getVoteOptionNum(Integer voteId){
		List<VoteOptionInfo> list=voteInfoMapper.getVoteOptionInfo(voteId);
		for (VoteOptionInfo voteOptionInfo:list){
			voteOptionInfo.setVoteNum(voteInfoMapper.getVotedOptionNum(voteOptionInfo.getVoteOptionId())==null?0:voteInfoMapper.getVotedOptionNum(voteOptionInfo.getVoteOptionId()));
		}
        return list;
    }

    
    /**
	 * @Title:updateStaffList
	 * @Description:更新投票参与员工列表
	 * @param1 voteId
	 * @param2 newStaffList
	 * @return String
	 */
	@Override
	public String updateStaffList(Integer voteId,  List<Integer> newStaffList) {
		int num = staffVoteMapper.deleteByVoteId(voteId);
		if(num==0) {
			return "数据异常";
			}else {
				for(int staffInfoId:newStaffList) {
					if(staffVoteMapper.select(staffInfoId, voteId)==1) {
						break;
					}
				    staffVoteMapper.insert(staffInfoId, voteId, 0);
				}
				return "更新员工成功";
			}
		
	}
    
	@Override
	public String updateDepart(Integer voteInfoId, Integer departmentId) {
		int num = departmentVoteMapper.deleteByVoteId(voteInfoId);
//		if(num==0) {
//			return "数据异常";
//			}else {
				if(departmentVoteMapper.selectDepartmentId(voteInfoId).size()==1) {
					return "该部门已存在！";
				}else
				departmentVoteMapper.insertWithOutId(departmentId, voteInfoId);
				return "更新部门成功";
			}
	//}
	
	@Override
	public String updateVoteEndtime(Integer voteId) {
		voteInfoMapper.updateVoteEndtime(new Date(),voteId);
		return "提前结束投票成功";
	}
		
	 @Override
		public VoteInfo updateVoteStatus(int voteid) {
			VoteInfo voteInfo = voteInfoMapper.selectByPrimaryKey(voteid);
			 Date now = new Date();
		        try {
		            Date createtime = voteInfo.getVoteBeginTime();
		            Date endtime = voteInfo.getVoteEndTime();
		            if (now.getTime() >= voteInfo.getVoteEndTime().getTime()) {
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
		        //System.out.println("------投票状态已更新------");
			return voteInfo;

		}

		@Override
		public List<VoteInfo> selectAllVoteInfo() {
			return voteInfoMapper.selectAllVoteInfo();
			 
		}
	 

	


}
