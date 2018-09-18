package com.hundsun.votesystem.model;

public class StaffInfo {
    //员工id
    private Integer staffId;

    //员工姓名
    private String siName;

    //员工号
    private String staffNum;

    //部门id
    private Integer staffDepartid;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getSiName() {
        return siName;
    }

    public void setSiName(String siName) {
        this.siName = siName == null ? null : siName.trim();
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum == null ? null : staffNum.trim();
    }

    public Integer getStaffDepartid() {
        return staffDepartid;
    }

    public void setStaffDepartid(Integer staffDepartid) {
        this.staffDepartid = staffDepartid;
    }
}