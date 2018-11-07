package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.StaffVoteDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface StaffVoteDetailInfoMapper {

    List<Integer> selectVoteInfoIdByPartId(int staffId);//参与过的投票

    int insertVoteInfo(Map<String, Object> param);

    //投票选项细节
    List<StaffVoteDetail> getDetail(@Param("staffId") int staffId, @Param("voteId") int voteId);
}