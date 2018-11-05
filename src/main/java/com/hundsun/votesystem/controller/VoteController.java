package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.VoteOption;
import com.hundsun.votesystem.model.VotePageInfo;
import com.hundsun.votesystem.service.impl.VoteProcessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ObjectStreamConstants;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("vote")
public class VoteController {
    @Autowired
    private VoteProcessServiceImpl voteProcessService;
    //    获得投票选项页面
    @PostMapping("getVotePage")
    public String getVotePage(HttpServletRequest request,HttpServletResponse response){
        ReturnData returnData = new ReturnData();
        int voteId = Integer.parseInt(request.getParameter("voteId"));//投票id
        int staffId = Integer.parseInt(request.getParameter("staffId"));//员工id
        VotePageInfo votePageInfo = new VotePageInfo();

        try{
            //查询投票状态、投票名，投票资格权限
            Map<String,Object> voteInfo = voteProcessService.selectVoteInfo(voteId);
            if ((Integer)voteInfo.get("vi_status")==0){
                returnData.setReturnMsg("error");
                returnData.setReturnMsgDetail("投票尚未开始！");
            }else if ((Integer)voteInfo.get("vi_status")==2){
                returnData.setReturnMsg("error");
                returnData.setReturnMsgDetail("投票已经结束！");
            }else{
                votePageInfo.setVoteName((String)voteInfo.get("vi_name"));//投票名
                votePageInfo.setVoteId(voteId);
                votePageInfo.setVoteType((Integer)voteInfo.get("vi_type"));//投票类型
                votePageInfo.setOptionNum((Integer)voteInfo.get("vi_optionnum"));//选项个数
                //查询选项id、选项名
                List<Map<String,Object>> optionInfo = voteProcessService.selectOptionInfo(voteId);
                votePageInfo.setOptionInfo(optionInfo);//整个页面所需信息
                int voteAuthorityType = (Integer) voteInfo.get("vi_authority_type");//投票资格权限
                if(voteAuthorityType==-1){
                    returnData.setReturnObject(votePageInfo);
                }else if(voteAuthorityType==0){//指定部门（可能有一个或者多个部门）
                    //查看员工所在部门
                    List<Integer> departIdList = new ArrayList<>();
                    departIdList = voteProcessService.selectDepartmentId(voteId);
                    int departId = voteProcessService.selectByStaffId(staffId);
                    if (departIdList.contains(departId)){
                        if (voteProcessService.isVoted(staffId,voteId)){
                            returnData.setReturnMsg("error");
                            returnData.setReturnMsgDetail("你已经参加过该投票！");
                        }else{
                            returnData.setReturnObject(votePageInfo);
                        }
                    }else{
                        returnData.setReturnMsg("error");
                        returnData.setReturnMsgDetail("你没有资格参加该投票！");
                    }
                }else{
                    if (!voteProcessService.isInStafflist(staffId,voteId)){
                        returnData.setReturnMsg("error");
                        returnData.setReturnMsgDetail("你没有资格参加投票！");
                    }else{
                        if(voteProcessService.isVoted(staffId,voteId)){
                            returnData.setReturnMsg("error");
                            returnData.setReturnMsgDetail("你已经参加过投票！");
                        }else{
                            returnData.setReturnObject(votePageInfo);
                        }
                    }
                }
            }
        }catch (Exception ex){
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(ex.getMessage());
        }
        return new Gson().toJson(returnData);
    }

    @PostMapping("vote")
    public  String vote(HttpServletRequest request,HttpServletResponse response){
        ReturnData returnData  = new ReturnData();
        Map<String,Object> param = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());

        try {
            int staffId = Integer.parseInt(request.getParameter("staffId"));//员工ID
            int voteId = Integer.parseInt((request.getParameter("voteId")));///投票信息ID
            Date voteTime = sdf.parse(currentTime);//投票时间
            String voteOption = request.getParameter("voteOption");//投票选项详细信息
            param.put("staffId",staffId);
            param.put("voteId",voteId);
            param.put("voteTime",voteTime);
            JsonArray jsonArray =  (JsonArray)new JsonParser().parse(voteOption);
            for (int i = 0;i<jsonArray.size();i++){
                JsonObject temp = (JsonObject) jsonArray.get(i);
                int  voteOptionId = Integer.parseInt(temp.get("voteOptionId").toString());
                String voteOptionDetail = temp.get("voteOptionDetail").toString();
                param.put("voteOptionId",voteOptionId);
                param.put("voteOptionDetail",voteOptionDetail);
                voteProcessService.vote(param);
            }
            returnData.setReturnMsgDetail("投票成功！");
        }catch (Exception ex){
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(ex.getMessage());
            return new Gson().toJson(returnData);
        }
        return new Gson().toJson(returnData);
    }
}

