package com.hundsun.votesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.util.ThreadVote;

@Service
public class AsynTaskService {
	@Autowired
    private VoteInfoServiceimpl voteServiceBase;
	
	@Async    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void threadVoteStatus(int voteId) {
		
			ThreadVote threadVote = new ThreadVote(voteId,voteServiceBase);
        	//System.out.println(ThreadVote.currentThread().getName());
            threadVote.start();
			
        	
	}

}
