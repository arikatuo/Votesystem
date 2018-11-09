package com.hundsun.votesystem.model.returndata;

public class VoteOptionInfo {
    private String voteName;
    private int voteNum;
    private int voteOptionId;
    private boolean myVote=false;

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public int getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(int voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    public boolean isMyVote() {
        return myVote;
    }

    public void setMyVote(boolean myVote) {
        this.myVote = myVote;
    }
}
