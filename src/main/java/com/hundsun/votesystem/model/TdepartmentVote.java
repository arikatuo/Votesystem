package com.hundsun.votesystem.model;

public class TdepartmentVote {
    private Integer Id;

    private Integer departMentId;

    private Integer voteInfoId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getDepartMentId() {
        return departMentId;
    }

    public void setDepartMentId(Integer departMentId) {
        this.departMentId = departMentId;
    }

    public Integer getVoteInfoId() {
        return voteInfoId;
    }

    public void setVoteInfoId(Integer voteInfoId) {
        this.voteInfoId = voteInfoId;
    }
}