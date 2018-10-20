package com.hundsun.votesystem.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;

public class ThreadVote extends Thread{
	
	private VoteInfo voteInfo;
	@Autowired
    private VoteServiceImpl voteServiceBase;
	
	public ThreadVote(VoteInfo voteInfo, VoteServiceImpl voteServiceBase) {
		this.voteInfo = voteInfo;
		this.voteServiceBase = voteServiceBase;
	}
	
	@Override  
    public void run() {
		//循环日期
		System.out.println("启动新线程");
		Calendar ca = Calendar.getInstance();
		Date curDate = new Date();
		//curDate.getTime()<=voteInfo.getVoteEndTime().getTime()
		while(curDate.compareTo(voteInfo.getVoteEndTime())<=0){
		      ca.setTime(curDate);
		      //更新投票状态
		      //System.out.println(voteInfo+"      "+ca.SECOND);
		      voteServiceBase.updateVoteStatus(voteInfo);
		      ca.add(ca.SECOND, 1);
		      curDate = ca.getTime();
		} 
	}

}
