package com.hundsun.votesystem.controller;

import com.google.gson.GsonBuilder;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.StaffVoteDetail;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteDetailInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;

import com.hundsun.votesystem.util.VoteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("voteinfo")
public class VoteInfoController {
	private static Logger logger = LoggerFactory.getLogger(VoteInfoController.class);
	@Autowired
	private VoteInfoService voteInfoService;
	@Autowired
	private VoteServiceImpl voteService;
	@Autowired
	private VoteDetailInfo voteDetailInfo;

	//通过voteInfoId获取投票详情
	@RequestMapping("finalvoteinfo")
	public String test(HttpServletRequest request, HttpServletResponse response) {
		VoteUtils.kuayuSolution(request, response);
		int voteInfoId = Integer.parseInt(request.getParameter("voteInfoId"));
		Integer staffNum = Integer.parseInt(request.getParameter("staffNum"));
		HashMap<String, Object> totalResult = null;
		HashMap<String, Integer> voterNum = new HashMap<>();
		ReturnData returnData = new ReturnData();
		Integer state = 1;
		boolean myVote=false;
		boolean canEndAhead = false;
		try {
			totalResult = new HashMap<>();
			VoteInfo voteInfo = voteInfoService.selectByPrimaryKey(voteInfoId);
			List<StaffInfo> staffInfos = voteService.getStaffInfoById(voteInfo.getVoteCreaterId());
			if (null != staffInfos) {
				voteInfo.setVoteCreaterName(staffInfos.get(0).getSiName());
			}
			if (staffNum.equals(voteInfo.getVoteCreaterId())) {
				voteInfo.setCanEndAhead(true);
			}
			List<HashMap<String, Integer>> num = voteInfoService.getVoterNum(voteInfoId);
			List<HashMap<String, Integer>> optionNum = voteInfoService.getVoteOptionNum(voteInfoId);//投票id，投票选项信息，投票人数
			List<StaffVoteDetail> staffVoteDetails=voteDetailInfo.selectDetail(staffNum,voteInfoId);
			for (HashMap hashMap: optionNum){
				for (StaffVoteDetail staffVoteDetail:staffVoteDetails){
					if (staffVoteDetail.getVoteId()==hashMap.get("voteId")){
						hashMap.put("myVote",true);
					}else {
						hashMap.put("myVote",false);
					}
				}
			}

			if (num.size() == 2) {
				voterNum.put("hasvote", num.get(0).get("voterNum"));
				voterNum.put("novote", num.get(1).get("voterNum"));
			} else if (num.size() == 1) {
				if (state.equals(num.get(0).get("voteState"))) {
					voterNum.put("hasvote", num.get(0).get("voterNum"));
					voterNum.put("novote", 0);
				} else {
					voterNum.put("novote", num.get(0).get("voterNum"));
					voterNum.put("hasvote", 0);
				}
			} else {
				voterNum.put("hasvote", 0);
				voterNum.put("nosvote", 0);
			}

			totalResult.put("voteInfo", voteInfo);
			totalResult.put("voternum", voterNum);
			totalResult.put("optionNum", optionNum);
			returnData.setReturnObject(totalResult);
		} catch (Exception e) {
			returnData.setReturnMsg("error");
			returnData.setReturnMsgDetail(e.getMessage());

		}
		return new GsonBuilder()
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create().toJson(returnData);
	}


}
