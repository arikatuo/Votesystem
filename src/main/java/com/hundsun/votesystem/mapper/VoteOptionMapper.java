package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.VoteOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface VoteOptionMapper {
    int deleteByPrimaryKey(Integer voteOptionId);

    int insert(VoteOption voteOption);

    List<Map<String,Object>> selectOptionInfo(int voteId);
}