package com.itboyst.facedemo.dto;

public class Ztraining_task_assess {
    private String zid;
    private String ztrainingtaskID;
    private String zgroup;
    private int zorder;
    private String ztechnicaldemand;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZtrainingtaskID() {
        return ztrainingtaskID;
    }

    public void setZtrainingtaskID(String ztrainingtaskID) {
        this.ztrainingtaskID = ztrainingtaskID;
    }

    public String getZgroup() {
        return zgroup;
    }

    public void setZgroup(String zgroup) {
        this.zgroup = zgroup;
    }

    public int getZorder() {
        return zorder;
    }

    public void setZorder(int zorder) {
        this.zorder = zorder;
    }

    public String getZtechnicaldemand() {
        return ztechnicaldemand;
    }

    public void setZtechnicaldemand(String ztechnicaldemand) {
        this.ztechnicaldemand = ztechnicaldemand;
    }

    @Override
    public String toString() {
        return "Ztraining_task_assess{" +
                "zid='" + zid + '\'' +
                ", ztrainingtaskID='" + ztrainingtaskID + '\'' +
                ", zgroup='" + zgroup + '\'' +
                ", zorder=" + zorder +
                ", ztechnicaldemand='" + ztechnicaldemand + '\'' +
                '}';
    }
}
