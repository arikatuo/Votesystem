package com.hundsun.votesystem.model;

import java.util.List;
import java.util.Map;

public class VotePageInfo {
	//投票信息
	private Integer voteId;
	//投票名
	private String voteName;
    //投票选项id及选项名称
    private List<Map<String,Object>> optionInfo;
    //投票种类，多选还是单选
    private int voteType;

	public String getVoteIntroduction() {
		return voteIntroduction;
	}

	public void setVoteIntroduction(String voteIntroduction) {
		this.voteIntroduction = voteIntroduction;
	}

	//若多选最多几个选项
	private String voteIntroduction;

    public int getVoteType() {
        return voteType;
    }

    public void setVoteType(int voteType) {
        this.voteType = voteType;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

    private int optionNum;

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}


	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public List<Map<String, Object>> getOptionInfo() {
		return optionInfo;
	}

	public void setOptionInfo(List<Map<String, Object>> optionInfo) {
		this.optionInfo = optionInfo;
	}
}
