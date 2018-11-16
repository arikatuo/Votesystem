package com.hundsun.votesystem.util;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext ac = event.getApplicationContext();
        VoteServiceImpl voteServiceBase = ac.getBean(VoteServiceImpl.class);
        List<VoteInfo> voteInfoList = voteServiceBase.selectAllVoteInfo();
        //System.out.println(voteInfoList.size());
        for(VoteInfo voteInfo:voteInfoList) {
        	//System.out.println(voteInfo.getVoteId());
        	//if(voteInfo.getVoteId().equals(1)) {
        	ThreadVote threadVote = new ThreadVote(voteInfo,voteServiceBase);
            threadVote.start();
        	//}
        }
        
    }
}
