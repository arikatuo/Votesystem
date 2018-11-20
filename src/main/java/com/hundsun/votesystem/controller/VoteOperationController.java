package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;
import com.hundsun.votesystem.util.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("operation")
public class VoteOperationController {
	@Autowired
	private VoteServiceImpl voteServiceBase;
	//删除投票
	@RequestMapping("deletevote")
	public String deleteVote(HttpServletRequest request, HttpServletResponse response) {
		VoteUtils.kuayuSolution(request,response);
		int voteInfoId = Integer.parseInt(request.getParameter("voteInfoId"));
		ReturnData returnData = new ReturnData();
		try {
			int num = voteServiceBase.deleteVote(voteInfoId);
			if (num == 0) {
				returnData.setReturnMsg("error");
				returnData.setReturnMsgDetail("删除失败");
				return new Gson().toJson(returnData);
			}
		} catch (Exception ex) {
			returnData.setReturnMsg("error");
			returnData.setReturnMsgDetail(ex.getMessage());
		}
		return new Gson().toJson(returnData);
	}
}

