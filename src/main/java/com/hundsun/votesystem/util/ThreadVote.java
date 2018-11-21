package com.hundsun.votesystem.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import com.hundsun.votesystem.service.impl.VoteInfoServiceimpl;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;


public class ThreadVote extends Thread{
	
	private Integer voteId;
	
	@Autowired
    private VoteInfoServiceimpl voteServiceBase;
	
	public ThreadVote(Integer voteId, VoteInfoServiceimpl voteServiceBase) {
		this.voteId = voteId;
		this.voteServiceBase = voteServiceBase;
	}
	
	@Override  
    public void run() {
		 try {
			//循环日期
				//System.out.println("启动新线程");
				Calendar ca = Calendar.getInstance();
				Date curDate = new Date();
				VoteInfo voteInfo = voteServiceBase.selectByPrimaryKey(voteId);
				do{
				      ca.setTime(curDate);
				      //更新投票状态
				      voteInfo = voteServiceBase.updateVoteStatus(voteId);
				      ca.add(ca.SECOND, 3);
				      curDate = ca.getTime();
				} while(curDate.compareTo(voteInfo.getVoteEndTime())<0);
         } catch (Exception e) {
             e.printStackTrace();
         }
		
	}

}
