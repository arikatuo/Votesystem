package com.hundsun.votesystem.mapper;

import com.hundsun.votesystem.model.StaffInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StaffInfoMapper {
    int deleteByPrimaryKey(Integer staffId);

    int insert(StaffInfo record);

    int insertSelective(StaffInfo record);

    StaffInfo selectByPrimaryKey(Integer staffId);

    StaffInfo selectByStaffNum(@Param("staffNum")String staffNum);

    List<StaffInfo> selectByDepId(Integer depId);

    List<StaffInfo> selectAll();

    int updateByPrimaryKeySelective(StaffInfo record);

    int updateByPrimaryKey(StaffInfo record);

    int selectByStaffId(int staffId);
}