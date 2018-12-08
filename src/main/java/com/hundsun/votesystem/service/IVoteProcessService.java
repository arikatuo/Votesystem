package com.hundsun.votesystem.service;


import com.hundsun.votesystem.model.VoteInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by sumimasah on 2018/10.
 */
@Service
public interface IVoteProcessService {
    void vote(Map<String ,Object> param);

    boolean isInStafflist(int staffId, int voteInfoId);

    boolean isVoted(int staffId, int voteInfoId);

    VoteInfo selectByPrimaryKey(int voteId);

    List<Map<String,Object>> selectOptionInfo(int voteId);

    Map<String,Object> selectVoteInfo(int voteId);

    List<Integer> selectDepartmentId(int voteId);

    int selectByStaffId(int staffId);

    void changeStaffVoteStatus(Map<String,Object>param);

    int selectStaffVoteInfo(Map<String,Object>param);

    int StaffVoteInfoNum(int staffId,int voteId);
}
