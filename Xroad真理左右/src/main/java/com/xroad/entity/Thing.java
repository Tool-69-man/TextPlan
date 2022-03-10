package com.xroad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Thing implements Serializable {
    private Integer tid;
    private String name;
    private String allFeel;


    public Thing(Integer tid, String name, String allFeel) {
        this.tid = tid;
        this.name = name;
        this.allFeel = allFeel;
    }

    public Thing() {
        super();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllFeel() {
        return allFeel;
    }

    public void setAllFeel(String allFeel) {
        this.allFeel = allFeel;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", allFeel='" + allFeel + '\'' +
                '}';
    }
}
