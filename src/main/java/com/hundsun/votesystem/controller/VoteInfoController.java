package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("voteinfo")
public class VoteInfoController {

    @Autowired
    private VoteInfoService voteInfoService;

    //通过voteInfoId获取投票详情
    @RequestMapping("finalvoteinfo")
    public String test(int voteInfoId){
        HashMap<String,Object> totalResult= null;
        HashMap<String,Integer> voterNum=new HashMap<>();
        ReturnData returnData=new ReturnData();
        try {
            totalResult = new HashMap<>();
            VoteInfo voteInfo=voteInfoService.selectByPrimaryKey(voteInfoId);
            List<HashMap<Integer,Integer>>  num=voteInfoService.getVoterNum(voteInfoId);
            List<HashMap<Integer,Integer>>  optionNum=voteInfoService.getVoteOptionNum(voteInfoId);
            voterNum.put("hasvote",num.get(0).get("voterNum"));
            voterNum.put("novote",num.get(1).get("voterNum"));
            totalResult.put("voteInfo",voteInfo);
            totalResult.put("voternum",voterNum);
            totalResult.put("optionNum",optionNum);
            returnData.setReturnObject(totalResult);
        } catch (Exception e) {
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(e.getMessage());
        }
        return new Gson().toJson(totalResult);
    }

}
