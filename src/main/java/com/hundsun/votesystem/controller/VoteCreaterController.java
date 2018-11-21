package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;
import com.hundsun.votesystem.util.ThreadVote;
import com.hundsun.votesystem.util.VoteConsant;
import com.hundsun.votesystem.util.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("votesys")
public class VoteCreaterController {
    @Autowired
    private VoteServiceImpl voteServiceBase;
    @RequestMapping("")
    public String hello(){
        return "Hello VoteSystem";
    }
    //通过id搜索员工
    @RequestMapping("getstaffbyid")
    public String getStaffById(int staffid){
        List<StaffInfo> staffInfos=new ArrayList<>();
        ReturnData returnData=new ReturnData();
        try {
            staffInfos=voteServiceBase.getStaffInfoById(staffid);
            returnData.setReturnObject(staffInfos);
        }catch (Exception ex){
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(ex.getMessage());
        }
        return new Gson().toJson(returnData);
    }
    //通过id搜索员工
    @RequestMapping("getstaffbynum")
    public String getStaffByNum(String staffNum){
        List<StaffInfo> staffInfos=new ArrayList<>();
        ReturnData returnData=new ReturnData();
        try {
            staffInfos=voteServiceBase.getStaffInfoByStaffNum(staffNum);
            returnData.setReturnObject(staffInfos);
        }catch (Exception ex){
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(ex.getMessage());
        }
        return new Gson().toJson(returnData);
    }

    //通过id搜索员工
    @RequestMapping("getstaffbydepid")
    public String getStaffByNum(int depId){
        List<StaffInfo> staffInfos=new ArrayList<>();
        ReturnData returnData=new ReturnData();
        try {
            staffInfos=voteServiceBase.getStaffInfoByDepId(depId);
            returnData.setReturnObject(staffInfos);
        }catch (Exception ex){
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(ex.getMessage());
        }
        return new Gson().toJson(returnData);
    }

    //获取所有员工信息
    @RequestMapping("getallstaff")
    public String getAllStaff(){
        List<StaffInfo> staffInfos=new ArrayList<>();
        ReturnData returnData=new ReturnData();
        try {
            staffInfos=voteServiceBase.getAllStaff();
            returnData.setReturnObject(staffInfos);
        }catch (Exception ex){
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(ex.getMessage());
        }
        return new Gson().toJson(returnData);
    }
    //创建投票
    @PostMapping("createvote")
    public String createVote(HttpServletRequest request, HttpServletResponse response){
        VoteUtils.kuayuSolution(request,response);
        List<String> voteOptionList = new ArrayList<>();//选项列表
        List<Integer> staffList=new ArrayList<>();//员工列表
        int departmentId=-1;//部门
        ReturnData returnData=new ReturnData();
        try{
            voteOptionList=VoteUtils.str2Stringlist(request.getParameter("voteOptionList"));
            int voteAuthorityType=1;
            String voteAuthorityTypeString=request.getParameter("viAuthorityType");
            if(voteAuthorityTypeString!=null)
                voteAuthorityType= Integer.parseInt(request.getParameter("viAuthorityType"));
            if(voteAuthorityType==1)
                staffList= VoteUtils.str2Integerlist(request.getParameter("staffList"));
            if(voteAuthorityType==0)
                departmentId= Integer.parseInt(request.getParameter("departmentId"));
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String voteName=request.getParameter("voteName");
            int voteCreaterId= Integer.parseInt(request.getParameter("voteCreaterId"));
            int voteType= Integer.parseInt(request.getParameter("voteType"));
            if(voteType!= VoteConsant.VOTE_TYPE_SINGLE&&voteType!= VoteConsant.VOTE_TYPE_MULTIPLE){
                returnData.setReturnMsg("error");
                returnData.setReturnMsgDetail("投票种类设置错误");
                return new Gson().toJson(returnData);
            }
            int voteTaskInfoId= Integer.parseInt(request.getParameter("voteTaskInfoId"));
            int voteOptionNum=1;
            //若投票为多选类型，需设置可选择数量
            if(voteType==1)
                voteOptionNum=Integer.parseInt(request.getParameter("voteOptionNum"));

            Date voteBeginTime=new Date(Long.parseLong(request.getParameter("voteBeginTime")));
            Date voteEndTime= new Date(Long.parseLong(request.getParameter("voteEndTime")));
            VoteInfo voteInfo=new VoteInfo(voteName,new Date(),voteBeginTime,voteEndTime,
                    0,voteCreaterId,voteType,voteTaskInfoId,voteOptionNum,voteAuthorityType);
            voteServiceBase.createVote(voteInfo,staffList,voteOptionList,departmentId,voteAuthorityType);
            returnData.setReturnObject(voteInfo);
         
            //启动一个实时更新的线程
           // ThreadVote threadVote = new ThreadVote(voteInfo,voteServiceBase);
           // threadVote.start();
        }catch (Exception ex){
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(ex.getMessage());
        }
        return new Gson().toJson(returnData);

    }



}
