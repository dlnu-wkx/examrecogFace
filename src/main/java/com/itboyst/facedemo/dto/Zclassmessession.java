package com.itboyst.facedemo.dto;

public class Zclassmessession {
    private String zname;
    private String taskid;
    private int kindid;
    private String assid;
    private int pages;

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public int getKindid() {
        return kindid;
    }

    public void setKindid(int kindid) {
        this.kindid = kindid;
    }

    public String getAssid() {
        return assid;
    }

    public void setAssid(String assid) {
        this.assid = assid;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Zclassmessession{" +
                "zname='" + zname + '\'' +
                ", taskid='" + taskid + '\'' +
                ", kindid=" + kindid +
                ", assid='" + assid + '\'' +
                ", pages=" + pages +
                '}';
    }
}
