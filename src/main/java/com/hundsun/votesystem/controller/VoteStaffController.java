package com.hundsun.votesystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
@RestController
@RequestMapping("votestaff")
public class VoteStaffController {
	
	@Autowired
    private VoteInfoService voteInfoService;
	
	//更新投票参与员工信息
    @RequestMapping("updateVoterByList")
    public String updateVoterByList(int voteId, List<StaffInfo> newStaffList) {
    	
    	ReturnData returnData=new ReturnData();
    	VoteInfo voteInfo = voteInfoService.selectByPrimaryKey(voteId);
	    	try {
	    		if(voteInfo.getVoteAuthorityType()!=1) {
	    			returnData.setReturnMsg("error");
					returnData.setReturnMsgDetail("投票资格权限有误！");
					return new Gson().toJson(returnData);
	        		
	        	}else { 
	    		    String result = voteInfoService.updateStaffList(voteId, newStaffList);
				    if(!result.equals("更新成功")) {
				    	returnData.setReturnMsg("error");
						returnData.setReturnMsgDetail("更新失败");
						return new Gson().toJson(returnData);
				    }
	        	}
			} catch (Exception ex) {
				returnData.setReturnMsg("error");
				returnData.setReturnMsgDetail(ex.getMessage());
			}
	    	return new Gson().toJson(returnData);
    	
    	
    }
    
    /*//更新投票参与部门信息
    public String updateVoterByDepart(int voteId, int departId) {
    	
    }*/
    

}
