package com.hundsun.votesystem.service;

import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.returndata.VoteInfoWithStaffNum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface VoteServiceBase {

    //通过id获取,下面这三种方法可以封装成一个方法
    List<StaffInfo>  getStaffInfoById(int staffId);

    //通过id获取
    List<StaffInfo>  getStaffInfoByStaffNum(String staffNum);

    //通过id获取
    List<StaffInfo>  getStaffInfoByDepId(int depId);

    //获取所有员工
    List<StaffInfo> getAllStaff();

    //获取不同类型的voteinfo
    Map<String,List<VoteInfoWithStaffNum>> getClassificationVoteInfo(int staffId);

    //创建投票
    int createVote(VoteInfo voteInfo,List<Integer> stafflist,List<String> voteOptionList);

}
