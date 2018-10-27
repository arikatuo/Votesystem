package com.hundsun.votesystem.model.returndata;

import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.model.VoteOption;

import java.util.Date;
import java.util.List;

public class VoteInfoWithStaffNum{
    private int staffNumVoted;//已投票人数
    private int staffNumNotVoted;//未投票人数
    private String VoteCreaterName;//投票创建人

    public String getVoteCreaterName() {
        return VoteCreaterName;
    }

    public void setVoteCreaterName(String voteCreaterName) {
        VoteCreaterName = voteCreaterName;
    }

    //id
    private Integer voteId;

    //投票名
    private String voteName;

    //投票创建时间
    private long voteCreateTime;

    //投票开始时间
    private long voteBeginTime;

    //投票结束时间
    private long voteEndTime;

    //投票状态，未开始，进行时，已结束
    private Integer voteStatus;

    //投票创建者
    private Integer voteCreaterId;

    //投票类型，单选多选
    private Integer voteType;

    //投票任务id
    private Integer voteTaskInfoId;

    //投票可选数量（多选用）
    private Integer voteOptionNum;


    public VoteInfoWithStaffNum(){

    }
    public VoteInfoWithStaffNum(VoteInfo voteInfo){
        this.setVoteCreateTime(voteInfo.getVoteCreateTime().getTime());
        this.setVoteBeginTime(voteInfo.getVoteBeginTime().getTime());
        this.setVoteCreaterId(voteInfo.getVoteCreaterId());
        this.setVoteEndTime(voteInfo.getVoteEndTime().getTime());
        this.setVoteName(voteInfo.getVoteName());
        this.setVoteId(voteInfo.getVoteId());
        this.setVoteStatus(voteInfo.getVoteStatus());
        this.setVoteType(voteInfo.getVoteType());
        this.staffNumNotVoted=123;
        this.staffNumVoted=110;
    }

    public int getStaffNumVoted() {
        return staffNumVoted;
    }

    public void setStaffNumVoted(int staffNumVoted) {
        this.staffNumVoted = staffNumVoted;
    }

    public int getStaffNumNotVoted() {
        return staffNumNotVoted;
    }

    public void setStaffNumNotVoted(int staffNumNotVoted) {
        this.staffNumNotVoted = staffNumNotVoted;
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
        this.voteName = voteName;
    }

    public long getVoteCreateTime() {
        return voteCreateTime;
    }

    public void setVoteCreateTime(long voteCreateTime) {
        this.voteCreateTime = voteCreateTime;
    }

    public long getVoteBeginTime() {
        return voteBeginTime;
    }

    public void setVoteBeginTime(long voteBeginTime) {
        this.voteBeginTime = voteBeginTime;
    }

    public long getVoteEndTime() {
        return voteEndTime;
    }

    public void setVoteEndTime(long voteEndTime) {
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
