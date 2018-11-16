package com.hundsun.votesystem.service;

import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.returndata.VoteOptionInfo;
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
    //获取投票参与人数，已投和未投
    List<HashMap<String,Integer>> getVoterNum(Integer voteInfoId);
    //每个选项有多少人投了
    List<VoteOptionInfo> getVoteOptionNum(Integer voteId);

  
    //更新投票参与员工列表
    String updateStaffList(Integer voteId,  List<Integer> newStaffList);
    //更新投票参与部门
    String updateDepart(Integer voteId, Integer departId );
    //更新投票结束时间
    String updateVoteEndtime(Integer voteId);
	
    
    
    
}
