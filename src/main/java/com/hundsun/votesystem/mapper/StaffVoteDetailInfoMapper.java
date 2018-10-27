package com.hundsun.votesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface StaffVoteDetailInfoMapper {

    int insertVoteInfo(Map<String, Object> param);

}