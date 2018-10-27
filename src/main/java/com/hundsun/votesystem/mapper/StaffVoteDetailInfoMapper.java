package com.hundsun.votesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface StaffVoteDetailInfoMapper {

    List<Integer> selectVoteInfoIdByPartId(int staffId);//参与过的投票
    int insertVoteInfo(Map<String, Object> param);

}