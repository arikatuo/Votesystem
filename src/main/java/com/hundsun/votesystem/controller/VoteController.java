package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.IVoteProcessService;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;
import com.hundsun.votesystem.util.ThreadVote;
import com.hundsun.votesystem.util.VoteConsant;
import com.hundsun.votesystem.util.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("votesys")
public class VoteController {
    @Autowired
    private IVoteProcessService voteProcessService;
    //    进行投票
    @RequestMapping("vote")
    public  String Vote(HttpServletRequest request,HttpServletResponse response){
//        判断是否具有投票资格
        ReturnData returnData  = new ReturnData();
        int staffId = Integer.parseInt(request.getParameter("staffId"));//员工ID
        int voteInfoId = Integer.parseInt((request.getParameter("voteInfroId")));///投票信息ID
        if (voteProcessService.isInStafflist (staffId,voteInfoId)){
            if (!voteProcessService.isVoted(staffId,voteInfoId)){
                try{
                    int voteOptionId = Integer.parseInt(request
                            .getParameter("voteOptionId"));//投票选项id
                    String optionDetail = request.getParameter("optionDetail");//投票选项详细信息
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String voteTime = sdf.format(new Date());//投票时间
                    Map<String,Object> param = new HashMap<String,Object>();
                    param.put("staffId",staffId);
                    param.put("voteInfoId",voteInfoId);
                    param.put("voteOptionId",voteOptionId);
                    param.put("optionDetail",optionDetail);
                    param.put("voteTime",voteTime);
                    return voteProcessService.vote(param);
                }catch (Exception ex){
                    returnData.setReturnMsg("error");
                    returnData.setReturnMsgDetail(ex.getMessage());
                    return new Gson().toJson(returnData);
                }
            }else{
                returnData.setReturnMsg("你已经过参加过该投票!");
                return new Gson().toJson(returnData);
            }
        }else{
            returnData.setReturnMsg("你没有资格参加该投票！");
            return new Gson().toJson(returnData);
        }
    }
 }
