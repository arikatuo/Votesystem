package com.hundsun.votesystem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteInfo {


    public List<VoteOption> getVoteOptionList() {
        return voteOptionList;
    }

    public void setVoteOptionList(VoteOption voteOption) {
        this.voteOptionList .add(voteOption);
    }

    public VoteInfo(){
        voteOptionList=new ArrayList<>();
    }


    public VoteInfo(VoteInfo vi){
        this.voteName = vi.voteName;
        //this.voteBeginTime = vi.voteBeginTime;
        //this.voteEndTime = vi.voteEndTime;
        this.voteStatus =vi. voteStatus;
        this.voteCreaterId = vi.voteCreaterId;
        this.voteType = vi.voteType;
        this.voteTaskInfoId = vi.voteTaskInfoId;
        this.voteOptionNum = vi.voteOptionNum;
        voteOptionList=new ArrayList<>();
    }
    public VoteInfo(String voteName, Date voteCreateTime ,Date voteBeginTime, Date voteEndTime,
                    Integer voteStatus, Integer voteCreaterId, Integer voteType, Integer voteTaskInfoId, Integer voteOptionNum,Integer voteAuthorityType) {
        this.voteName = voteName;
        this.voteCreateTime=voteCreateTime;
        this.voteBeginTime = voteBeginTime;
        this.voteEndTime = voteEndTime;
        this.voteStatus = voteStatus;
        this.voteCreaterId = voteCreaterId;
        this.voteType = voteType;
        this.voteTaskInfoId = voteTaskInfoId;
        this.voteOptionNum = voteOptionNum;
        this.voteAuthorityType=voteAuthorityType;
        voteOptionList=new ArrayList<>();

    }

    //投票选项
    private List<VoteOption> voteOptionList;

    //id
    private Integer voteId;

    //投票名
    private String voteName;

    //投票创建时间
    private Date voteCreateTime;

    //投票开始时间
    private Date voteBeginTime;

    //投票结束时间
    private Date voteEndTime;

    //投票状态，未开始，进行时，已结束
    private Integer voteStatus;

    //投票创建者
    private Integer voteCreaterId;

    public String getVoteCreaterName() {
        return voteCreaterName;
    }

    public void setVoteCreaterName(String voteCreaterName) {
        this.voteCreaterName = voteCreaterName;
    }

    //投票创建者
    private String voteCreaterName;

    //投票类型，单选多选
    private Integer voteType;

    //投票任务id
    private Integer voteTaskInfoId;

    //投票可选数量（多选用）
    private Integer voteOptionNum;

    //投票种类
    private Integer voteAuthorityType;

    //是否能提前结束
    private boolean canEndAhead;

    private String voteIntroduction;

    public String getVoteIntroduction() {
        return voteIntroduction;
    }

    public void setVoteIntroduction(String voteIntroduction) {
        this.voteIntroduction = voteIntroduction;
    }

    public boolean isCanEndAhead() {
        return canEndAhead;
    }

    public void setCanEndAhead(boolean canEndAhead) {
        this.canEndAhead = canEndAhead;
    }

    public void setVoteOptionList(List<VoteOption> voteOptionList) {
        this.voteOptionList = voteOptionList;
    }

    public Integer getVoteAuthorityType() {
        return voteAuthorityType;
    }

    public void setVoteAuthorityType(Integer voteAuthorityType) {
        this.voteAuthorityType = voteAuthorityType;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName == null ? null : voteName.trim();
    }

    public Date getVoteCreateTime() {
        return voteCreateTime;
    }

    public void setVoteCreateTime(Date voteCreateTime) {
        this.voteCreateTime = voteCreateTime;
    }

    public Date getVoteBeginTime() {
        return voteBeginTime;
    }

    public void setVoteBeginTime(Date voteBeginTime) {
        this.voteBeginTime = voteBeginTime;
    }

    public Date getVoteEndTime() {
        return voteEndTime;
    }

    public void setVoteEndTime(Date voteEndTime) {
        this.voteEndTime = voteEndTime;
    }

    public Integer getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(Integer voteStatus) {
        this.voteStatus = voteStatus;
    }

    public Integer getVoteCreaterId() {
        return voteCreaterId;
    }

    public void setVoteCreaterId(Integer voteCreaterId) {
        this.voteCreaterId = voteCreaterId;
    }

    public Integer getVoteType() {
        return voteType;
    }

    public void setVoteType(Integer voteType) {
        this.voteType = voteType;
    }

    public Integer getVoteTaskInfoId() {
        return voteTaskInfoId;
    }

    public void setVoteTaskInfoId(Integer voteTaskInfoId) {
        this.voteTaskInfoId = voteTaskInfoId;
    }

    public Integer getVoteOptionNum() {
        return voteOptionNum;
    }

    public void setVoteOptionNum(Integer voteOptionNum) {
        this.voteOptionNum = voteOptionNum;
    }
}