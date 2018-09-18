package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.VoteOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VoteOptionMapper {
    int deleteByPrimaryKey(Integer voteOptionId);

    int insert(VoteOption voteOption);


}