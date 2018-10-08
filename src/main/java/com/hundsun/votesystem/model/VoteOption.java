package com.hundsun.votesystem.model;

public class VoteOption {
    private Integer voteOptionId;

    private String voteOptionName;//选项名

    private Integer voteInfoId;//投票id

    public Integer getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(Integer voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    public String getVoteOptionName() {
        return voteOptionName;
    }

    public void setVoteOptionName(String voteOptionName) {
        this.voteOptionName = voteOptionName == null ? null : voteOptionName.trim();
    }

    public Integer getVoteInfoId() {
        return voteInfoId;
    }

    public void setVoteInfoId(Integer voteInfoId) {
        this.voteInfoId = voteInfoId;
    }
}