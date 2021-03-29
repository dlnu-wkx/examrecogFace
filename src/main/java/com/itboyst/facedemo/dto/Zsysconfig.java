package com.itboyst.facedemo.dto;

public class Zsysconfig {
    private String zname;
    private String zvalue;

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZvalue() {
        return zvalue;
    }

    public void setZvalue(String zvalue) {
        this.zvalue = zvalue;
    }

    @Override
    public String toString() {
        return "Zsysconfig{" +
                "zname='" + zname + '\'' +
                ", zvalue='" + zvalue + '\'' +
                '}';
    }
}
