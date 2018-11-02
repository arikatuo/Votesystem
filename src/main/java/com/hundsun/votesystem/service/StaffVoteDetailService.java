package com.hundsun.votesystem.service;

import com.hundsun.votesystem.model.StaffVoteDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffVoteDetailService {
    List<StaffVoteDetail> getDetail(@Param("staffID") int staffId, @Param("voteId") int voteId);
}
