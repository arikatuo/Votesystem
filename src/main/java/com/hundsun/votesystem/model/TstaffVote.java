package com.hundsun.votesystem.model;

public class TstaffVote {

	//投票任务投票人关联表id
	private Integer id;
	//投票人id
	private Integer sv_siid;
	//投票id
	private Integer sv_viid;
	//是否投票
	private boolean sv_isvoted;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSv_siid() {
		return sv_siid;
	}
	public void setSv_siid(Integer sv_siid) {
		this.sv_siid = sv_siid;
	}
	public Integer getSv_viid() {
		return sv_viid;
	}
	public void setSv_viid(Integer sv_viid) {
		this.sv_viid = sv_viid;
	}
	public boolean isSv_isvoted() {
		return sv_isvoted;
	}
	public void setSv_isvoted(boolean sv_isvoted) {
		this.sv_isvoted = sv_isvoted;
	}

}
