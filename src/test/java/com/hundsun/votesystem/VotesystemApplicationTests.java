package com.hundsun.votesystem;

import com.hundsun.votesystem.mapper.StaffInfoMapper;
import com.hundsun.votesystem.mapper.TstaffVoteMapper;
import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.mapper.VoteOptionMapper;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.VoteOption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VotesystemApplicationTests {

	@Autowired
	private StaffInfoMapper staffInfoMapper;

	@Autowired
	private VoteInfoMapper voteInfoMapper;

	@Autowired
	private TstaffVoteMapper tstaffVoteMapper;

	@Autowired
	private VoteOptionMapper voteOptionMapper;
	@Test
	public void contextLoads() {

//		 public VoteInfo(String voteName, Date voteBeginTime, Date voteEndTime, Integer voteStatus, Integer voteCreaterId, Integer voteType, Integer voteTaskInfoId, Integer voteOptionNum) {
//			this.voteName = voteName;
//			this.voteBeginTime = voteBeginTime;
//			this.voteEndTime = voteEndTime;
//			this.voteStatus = voteStatus;
//			this.voteCreaterId = voteCreaterId;
//			this.voteType = voteType;
//			this.voteTaskInfoId = voteTaskInfoId;
//			this.voteOptionNum = voteOptionNum;
//		}
		Date d=new Date();

		//tstaffVoteMapper.insert(20,30);

		VoteInfo voteInfo=new VoteInfo("测试投票名333",d,d,d,1,1,1,1,-1);

//		System.out.println(voteInfo.getVoteId()+"AA");
//		voteInfoMapper.insert(voteInfo);
//		System.out.println(voteInfo.getVoteId());
//		VoteOption voteOption=new VoteOption();
//		voteOption.setVoteOptionId(2);
//		voteOption.setVoteOptionName("选项0910");
//		voteOption.setVoteInfoId(1);
//		voteOptionMapper.insert(voteOption);
//		System.out.println(staffInfoMapper.selectByPrimaryKey(1).getSiName());
//		System.out.println(staffInfoMapper.selectByStaffNum("03327").getSiName());
		List<StaffInfo> staffInfos=staffInfoMapper.selectByDepId(1);
		for(StaffInfo s:staffInfos)
			System.out.println(s.getSiName());

	}

}
