package com.hundsun.votesystem.service;


import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by sumimasah on 2018/10.
 */
@Service
public interface IVoteProcessService {
    String vote(Map<String ,Object> param);

    boolean isInStafflist(int staffId, int voteInfoId);

    boolean isVoted(int staffId, int voteInfoId);
}
