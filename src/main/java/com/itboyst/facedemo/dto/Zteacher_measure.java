package com.itboyst.facedemo.dto;

public class Zteacher_measure {
    private String zid;
    private String zname;
    private String zidentity;
    private String zselfcheck;
    private String zteachercheck;
    private String ztaskinputid;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZidentity() {
        return zidentity;
    }

    public void setZidentity(String zidentity) {
        this.zidentity = zidentity;
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

    public String getZtaskinputid() {
        return ztaskinputid;
    }

    public void setZtaskinputid(String ztaskinputid) {
        this.ztaskinputid = ztaskinputid;
    }

    @Override
    public String toString() {
        return "Zteacher_measure{" +
                "zid='" + zid + '\'' +
                ", zname='" + zname + '\'' +
                ", zidentity='" + zidentity + '\'' +
                ", zselfcheck='" + zselfcheck + '\'' +
                ", zteachercheck='" + zteachercheck + '\'' +
                ", ztaskinputid='" + ztaskinputid + '\'' +
                '}';
    }
}
