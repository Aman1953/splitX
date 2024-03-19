package com.example.splitx.models;

import java.io.Serializable;
import java.util.HashMap;

public class Payable implements Serializable {

    private String name;
    private final HashMap<String, Float> owedList = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addOwer(String name, float amount){
        owedList.put(name, amount);
    }

    public HashMap<String, Float> getOwedList(){
        return owedList;
    }
}
