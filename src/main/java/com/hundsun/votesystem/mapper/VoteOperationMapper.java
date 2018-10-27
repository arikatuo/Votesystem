package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.VoteOption;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VoteOperationMapper {
    int deleteVote(Integer voteInfoId);
}