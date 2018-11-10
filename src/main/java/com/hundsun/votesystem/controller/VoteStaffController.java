package com.hundsun.votesystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import com.hundsun.votesystem.util.VoteUtils;
@RestController
@RequestMapping("votesys")
public class VoteStaffController {
	
	@Autowired
    private VoteInfoService voteInfoService;
	
	//更新投票参与员工信息
    @PostMapping("updatevoterlist")
    public String updateVoterByList(HttpServletRequest request,HttpServletResponse response) {
    	int voteId = Integer.parseInt(request.getParameter("voteId"));//投票任务id
    	List<Integer> newStaffIdList = VoteUtils.str2Integerlist(request.getParameter("newStaffIdList"));//投票员工列表
    	ReturnData returnData=new ReturnData();
    	VoteInfo voteInfo = voteInfoService.selectByPrimaryKey(voteId);
	    	try {
	    		if(voteInfo.getVoteAuthorityType()!=1 || voteInfo.getVoteAuthorityType().equals(null)) {
	    			returnData.setReturnMsg("error");
					returnData.setReturnMsgDetail("投票资格权限有误！");
					return new Gson().toJson(returnData);
	        		
	        	}else { 
	    		    String result = voteInfoService.updateStaffList(voteId, newStaffIdList);
				    if(!result.equals("更新员工成功")) {
				    	returnData.setReturnMsg("error");
						returnData.setReturnMsgDetail("更新员工失败");
						return new Gson().toJson(returnData);
				    }
				    returnData.setReturnMsg("success");
					returnData.setReturnMsgDetail("更新员工成功");
	        	}
	    		
			} catch (Exception ex) {
				returnData.setReturnMsg("error");
				returnData.setReturnMsgDetail(ex.getMessage());
			}
	    	return new Gson().toJson(returnData);
    	
    	
    }
    
    //更新投票参与部门信息
    @PostMapping("updateVoterByDepart")
    public String updateVoterByDepart(int voteId, int departId) {
    	ReturnData returnData=new ReturnData();
    	VoteInfo voteInfo = voteInfoService.selectByPrimaryKey(voteId);
    	//System.out.println(voteInfo.getVoteAuthorityType());
    	try {
    		if(voteInfo.getVoteAuthorityType()!=0 || voteInfo.getVoteAuthorityType().equals(null)) {
    			returnData.setReturnMsg("error");
				returnData.setReturnMsgDetail("投票资格权限有误！");
				return new Gson().toJson(returnData);
        		
        	}else { 
    		    String result = voteInfoService.updateDepart(voteId, departId);
			    if(!result.equals("更新部门成功")) {
			    	returnData.setReturnMsg("error");
					returnData.setReturnMsgDetail("更新部门失败");
					return new Gson().toJson(returnData);
			    }
			    returnData.setReturnMsg("success");
				returnData.setReturnMsgDetail("更新部门成功");
				//return new Gson().toJson(returnData);
        	}
		} catch (Exception ex) {
			returnData.setReturnMsg("error");
			returnData.setReturnMsgDetail(ex.getMessage());
		}
    	return new Gson().toJson(returnData);
	
    }
    

}
