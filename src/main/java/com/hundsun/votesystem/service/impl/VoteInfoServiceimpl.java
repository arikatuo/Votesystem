package com.hundsun.votesystem.service.impl;

import com.hundsun.votesystem.mapper.TdepartmentVoteMapper;
import com.hundsun.votesystem.mapper.TstaffVoteMapper;
import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.TstaffVote;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public List<HashMap<String,Integer>> getVoteOptionNum(Integer voteId){
        List<HashMap<String,Integer>> list=voteInfoMapper.getVoteOptionNum(voteId);
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
		if(num==0) {
			return "数据异常";
			}else {
				departmentVoteMapper.insertWithOutId(departmentId, voteInfoId);
				return "更新部门成功";
			}
		
	}
    
	

	


}
