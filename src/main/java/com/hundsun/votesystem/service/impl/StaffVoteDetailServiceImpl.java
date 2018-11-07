package com.hundsun.votesystem.service.impl;

import com.hundsun.votesystem.mapper.StaffVoteDetailInfoMapper;
import com.hundsun.votesystem.mapper.TdepartmentVoteMapper;
import com.hundsun.votesystem.mapper.TstaffVoteMapper;
import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.model.StaffVoteDetail;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.StaffVoteDetailService;
import com.hundsun.votesystem.service.VoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sumimasah on 2018/10.
 */

@Service
public class StaffVoteDetailServiceImpl implements StaffVoteDetailService {
    @Autowired
    private StaffVoteDetailInfoMapper staffVoteDetailInfoMapper;

    @Override
    public List<StaffVoteDetail> getDetail(int staffId, int voteId) {
        return staffVoteDetailInfoMapper.getDetail(staffId, voteId);
    }
}
