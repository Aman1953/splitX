package com.example.splitx.models;

public class Ower{
    private String name;

    private float amount;

    private float payable;

    private Ower payTo;

    public float getPayable() {
        return payable;
    }

    public void setPayable(float payable) {
        this.payable = payable;
    }

    public Ower getPayTo() {
        return payTo;
    }

    public void setPayTo(Ower payTo) {
        this.payTo = payTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Ower(String name, float amount){}
}
