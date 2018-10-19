package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
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
    @RequestMapping("voteinfo")
    public String test(int voteInfoId){
        HashMap<String,Integer> voterNum=new HashMap<>();
        HashMap<String,Object> totalResult=new HashMap<>();
        VoteInfo voteInfo=voteInfoService.selectByPrimaryKey(voteInfoId);
        List<HashMap<Integer,Integer>>  num=voteInfoService.getVoterNum(voteInfoId);
        List<HashMap<Integer,Integer>>  optionNum=voteInfoService.getVoteOptionNum(voteInfoId);
        voterNum.put("hasvote",num.get(0).get("voterNum"));
        voterNum.put("novote",num.get(1).get("voterNum"));
        totalResult.put("voteInfo",voteInfo);
        totalResult.put("voternum",num);
        totalResult.put("optionNum",optionNum);
        return new Gson().toJson(totalResult);
    }

}
