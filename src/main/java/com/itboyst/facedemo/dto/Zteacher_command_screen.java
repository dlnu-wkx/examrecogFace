package com.itboyst.facedemo.dto;

public class Zteacher_command_screen {
    private String zid;
    private String ztype;
    private String zcontent;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
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

    @Override
    public String toString() {
        return "Zteacher_command_screen{" +
                "zid='" + zid + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zcontent='" + zcontent + '\'' +
                '}';
    }
}
