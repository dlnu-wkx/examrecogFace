package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zselect_message {
    private int id;
    private String studentname;
    private String trainingroomname;
    private Timestamp mestime;
    private Timestamp deltetime;
    private String facilityname;
    private String isintype;
    private String leavereason;
    private String leavetype;
    private String approver;
    private String testtype;
    private String score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getTrainingroomname() {
        return trainingroomname;
    }

    public void setTrainingroomname(String trainingroomname) {
        this.trainingroomname = trainingroomname;
    }

    public Timestamp getMestime() {
        return mestime;
    }

    public void setMestime(Timestamp mestime) {
        this.mestime = mestime;
    }

    public Timestamp getDeltetime() {
        return deltetime;
    }

    public void setDeltetime(Timestamp deltetime) {
        this.deltetime = deltetime;
    }

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public String getIsintype() {
        return isintype;
    }

    public void setIsintype(String isintype) {
        this.isintype = isintype;
    }

    public String getLeavereason() {
        return leavereason;
    }

    public void setLeavereason(String leavereason) {
        this.leavereason = leavereason;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getTesttype() {
        return testtype;
    }

    public void setTesttype(String testtype) {
        this.testtype = testtype;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Zselect_message{" +
                "id=" + id +
                ", studentname='" + studentname + '\'' +
                ", trainingroomname='" + trainingroomname + '\'' +
                ", mestime=" + mestime +
                ", deltetime=" + deltetime +
                ", facilityname='" + facilityname + '\'' +
                ", isintype='" + isintype + '\'' +
                ", leavereason='" + leavereason + '\'' +
                ", leavetype='" + leavetype + '\'' +
                ", approver='" + approver + '\'' +
                ", testtype='" + testtype + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
