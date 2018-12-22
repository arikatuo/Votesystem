package com.hundsun.votesystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hundsun.votesystem.util.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteServiceBase;

@RestController
@RequestMapping("votesys")
public class UserInfoController {
	
	@Autowired
    private VoteServiceBase voteServiceBase;
	
	//用户登陆
    @PostMapping("userLogin")
    public String userLogin(HttpServletRequest request,HttpServletResponse response) {
		VoteUtils.kuayuSolution(request,response);
    	String staffnum = request.getParameter("staffnum") ;
    	String staffname = request.getParameter("staffname");
    	ReturnData returnData=new ReturnData();
    	List<StaffInfo> staffInfoList = voteServiceBase.getStaffInfoByStaffNum(staffnum);
    	try {
    			for(StaffInfo staffInfo : staffInfoList) {
        			if(staffInfo!=null && staffInfo.getSiName().equals(staffname)) {
        	            returnData.setReturnObject(staffInfo);
        				returnData.setReturnMsg("success");
        				returnData.setReturnMsgDetail("用户登陆成功！");
        				return new Gson().toJson(returnData);
                	}else { 
                		returnData.setReturnMsg("error");
        				returnData.setReturnMsgDetail("用户登陆信息有误！");
        				//return new Gson().toJson(returnData);
                	}
        		}
    		
		} catch (Exception ex) {
			returnData.setReturnMsg("error");
			returnData.setReturnMsgDetail(ex.getMessage());
		}
    	return new Gson().toJson(returnData);
	
    }
	

}
