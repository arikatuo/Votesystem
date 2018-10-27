package com.hundsun.votesystem;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hundsun.votesystem.mapper.StaffInfoMapper;
import com.hundsun.votesystem.mapper.TstaffVoteMapper;
import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.mapper.VoteOptionMapper;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.VoteOption;
import com.hundsun.votesystem.model.returndata.VoteInfoWithStaffNum;
import com.hundsun.votesystem.service.VoteInfoService;
import com.hundsun.votesystem.service.VoteServiceBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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

	@Autowired
	private VoteInfoService voteInfoService;

	@Autowired
	private VoteServiceBase voteServiceBase;
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
//		Date d=new Date();
//
//		//tstaffVoteMapper.insert(20,30);
//
//		VoteInfo voteInfo=new VoteInfo("测试投票名333",d,d,d,1,1,1,1,-1);
//
////		System.out.println(voteInfo.getVoteId()+"AA");
////		voteInfoMapper.insert(voteInfo);
////		System.out.println(voteInfo.getVoteId());
////		VoteOption voteOption=new VoteOption();
////		voteOption.setVoteOptionId(2);
////		voteOption.setVoteOptionName("选项0910");
////		voteOption.setVoteInfoId(1);
////		voteOptionMapper.insert(voteOption);
////		System.out.println(staffInfoMapper.selectByPrimaryKey(1).getSiName());
////		System.out.println(staffInfoMapper.selectByStaffNum("03327").getSiName());
//		List<StaffInfo> staffInfos=staffInfoMapper.selectByDepId(1);
//		for(StaffInfo s:staffInfos)
//			System.out.println(s.getSiName());

		Map<String,List<VoteInfoWithStaffNum>> map=voteServiceBase.getClassificationVoteInfo(2);

		System.out.println(new Gson().toJson(map));

//		List<HashMap<String,Integer>> listmap=voteInfoService.getVoterNum(2);
//
//		for(HashMap<String,Integer> hashMap:listmap){
//			System.out.println("==================");
//			Set<String> set=hashMap.keySet();
//			for(String key:set){
//				String a=String.valueOf(hashMap.get(key));
//				int aa=Integer.parseInt(a);
//				System.out.println(key+","+aa);
//			}
//			System.out.println("++++++++++++++++++");
//		}

//		Set<String> keys=map.keySet();
//		for(String s:keys){
//			System.out.println(s+":");
//			List<VoteInfo> list=map.get(s);
//			for(VoteInfo voteInfo:list)
//				System.out.println(voteInfo.getVoteName()+","+voteInfo.getVoteCreaterId()+","+voteInfo.getVoteStatus());
//		}

//		List<Integer> voteInfoIdList=tstaffVoteMapper.getVoteInfoIdList(1);
//		for(int i:voteInfoIdList){
//			VoteInfo voteInfo=voteInfoMapper.selectByPrimaryKey(i);
//			System.out.println(voteInfo.getVoteId()+","+voteInfo.getVoteName());
//		}

	}

}
