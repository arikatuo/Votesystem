package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.VoteInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VoteInfoMapper {
    int deleteByPrimaryKey(Integer voteId);

    int insert(VoteInfo record);

    int insertSelective(VoteInfo record);

    VoteInfo selectByPrimaryKey(Integer voteId);

    int updateByPrimaryKeySelective(VoteInfo record);

    int updateByPrimaryKey(VoteInfo record);
}