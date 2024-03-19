package com.example.splitx.models;

import java.util.ArrayList;

public class PayableList {
    private static final ArrayList<Payable> value = new ArrayList<>();

    public static void addElement(Payable payable){
        value.add(payable);
    }

    public  static  void removeElement(int index){
        value.remove(index);
    }

    public  static  void removeElement(Payable payable){
        value.remove(payable);
    }

    public  static  Payable get(int index){
        return value.get(index);
    }

    public static ArrayList<Payable> getList(){
        return value;
    }

    public static void clearList(){
        value.clear();
    }
}
