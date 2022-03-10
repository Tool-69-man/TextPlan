package com.xroad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Pie implements Serializable {
    String name;
    String value;

    public Pie() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Pie(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "name :'" + name + '\'' +
                ", value :'" + value + '\'' +
                '}';
    }
}
