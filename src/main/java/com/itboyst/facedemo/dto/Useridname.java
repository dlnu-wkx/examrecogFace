package com.itboyst.facedemo.dto;

public class Useridname {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Useridname{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
