package com.itboyst.facedemo.dto;

public class Ztraining_task_content {
    private String zid;
    private String ztrainingtaskID;
    private int zorder;
    private String ztype;
    private String zcontent;
    private String ztitle;

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

    public int getZorder() {
        return zorder;
    }

    public void setZorder(int zorder) {
        this.zorder = zorder;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZcontent() {
        return zcontent;
    }

    public void setZcontent(String zcontent) {
        this.zcontent = zcontent;
    }

    public String getZtitle() {
        return ztitle;
    }

    public void setZtitle(String ztitle) {
        this.ztitle = ztitle;
    }

    @Override
    public String toString() {
        return "Ztraining_task_content{" +
                "zid='" + zid + '\'' +
                ", ztrainingtaskID='" + ztrainingtaskID + '\'' +
                ", zorder=" + zorder +
                ", ztype='" + ztype + '\'' +
                ", zcontent='" + zcontent + '\'' +
                ", ztitle='" + ztitle + '\'' +
                '}';
    }
}
