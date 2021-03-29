package com.itboyst.facedemo.dto;

public class Ztask_input {
    private String zid;
    private String ztrainingtaskassessID;
    private String zassignscheduleID;
    private String zselfcheck;
    private String zteachercheck;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZtrainingtaskassessID() {
        return ztrainingtaskassessID;
    }

    public void setZtrainingtaskassessID(String ztrainingtaskassessID) {
        this.ztrainingtaskassessID = ztrainingtaskassessID;
    }

    public String getZassignscheduleID() {
        return zassignscheduleID;
    }

    public void setZassignscheduleID(String zassignscheduleID) {
        this.zassignscheduleID = zassignscheduleID;
    }

    public String getZselfcheck() {
        return zselfcheck;
    }

    public void setZselfcheck(String zselfcheck) {
        this.zselfcheck = zselfcheck;
    }

    public String getZteachercheck() {
        return zteachercheck;
    }

    public void setZteachercheck(String zteachercheck) {
        this.zteachercheck = zteachercheck;
    }

    @Override
    public String toString() {
        return "Ztask_input{" +
                "zid='" + zid + '\'' +
                ", ztrainingtaskassessID='" + ztrainingtaskassessID + '\'' +
                ", zassignscheduleID='" + zassignscheduleID + '\'' +
                ", zselfcheck='" + zselfcheck + '\'' +
                ", zteachercheck='" + zteachercheck + '\'' +
                '}';
    }
}
