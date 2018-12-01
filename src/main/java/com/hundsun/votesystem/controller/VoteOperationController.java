package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.service.impl.VoteProcessServiceImpl;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;
import com.hundsun.votesystem.util.VoteUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("operation")
public class VoteOperationController {
	Logger logger = Logger.getLogger(VoteOperationController.class);
	@Autowired
	private VoteServiceImpl voteServiceBase;
	@Autowired
	private VoteProcessServiceImpl voteProcessService;

	//删除投票
	@RequestMapping("deletevote")
	public String deleteVote(HttpServletRequest request, HttpServletResponse response) {
		VoteUtils.kuayuSolution(request, response);
		ReturnData returnData = new ReturnData();
		int voteInfoId = Integer.parseInt(request.getParameter("voteInfoId"));
		Map<String, Object> voteInfo = voteProcessService.selectVoteInfo(voteInfoId);
		if (null == voteInfo) {
			returnData.setReturnMsg("error");
			returnData.setReturnMsgDetail("不存在该投票");
			logger.debug("不存在id为" + voteInfoId + "的投票");
			return new Gson().toJson(returnData);
		}

		try {
			int num = voteServiceBase.deleteVote(voteInfoId);
		} catch (Exception ex) {
			returnData.setReturnMsg("error");
			returnData.setReturnMsgDetail("删除失败");
			logger.error(ex);
		}
		return new Gson().toJson(returnData);
	}
}

