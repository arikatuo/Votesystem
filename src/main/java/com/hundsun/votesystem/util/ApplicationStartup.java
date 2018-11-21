package com.hundsun.votesystem.util;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.impl.AsynTaskService;
import com.hundsun.votesystem.service.impl.VoteInfoServiceimpl;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	
	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext ac = event.getApplicationContext();
        VoteInfoServiceimpl voteServiceBase = ac.getBean(VoteInfoServiceimpl.class);
        AsynTaskService asynTaskService = ac.getBean(AsynTaskService.class);
        List<VoteInfo> voteInfoList = voteServiceBase.selectAllVoteInfo();
        for(VoteInfo voteInfo:voteInfoList) {
        	asynTaskService.threadVoteStatus(voteInfo.getVoteId());
        }
        
    }
}
