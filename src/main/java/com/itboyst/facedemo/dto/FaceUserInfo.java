package com.itboyst.facedemo.dto;


public class FaceUserInfo {
    private String faceId;
    private String name;
    private Integer similarValue;
    private byte[] faceFeature;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSimilarValue() {
        return similarValue;
    }

    public void setSimilarValue(Integer similarValue) {
        this.similarValue = similarValue;
    }

    public byte[] getFaceFeature() {
        return faceFeature;
    }

    public void setFaceFeature(byte[] faceFeature) {
        this.faceFeature = faceFeature;
    }

}
