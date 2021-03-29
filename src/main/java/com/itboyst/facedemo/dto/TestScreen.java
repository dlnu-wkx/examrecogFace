package com.itboyst.facedemo.dto;

public class TestScreen {
    private String id;
    private String filename;
    private String type;
    private String position;
    private int orders;
    private int timein;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getTimein() {
        return timein;
    }

    public void setTimein(int timein) {
        this.timein = timein;
    }

    @Override
    public String toString() {
        return "TestScreen{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", type='" + type + '\'' +
                ", position='" + position + '\'' +
                ", orders=" + orders +
                ", timein=" + timein +
                '}';
    }
}
