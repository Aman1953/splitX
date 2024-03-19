package com.example.splitx.models;

import java.sql.Array;
import java.util.ArrayList;

/**
 *
 */
public class OwerList {

    /**
     *
     */
    private static final ArrayList<Ower> value = new ArrayList<>();

    /**
     *
     * @param position
     * @return
     */
    public static Ower getValue(int position){
        return value.get(position);
    }

    /**
     * Add new element to the end of the array list
     * @param data
     */
    public static void addElement(Ower data){
        value.add(data);
    }

    /**
     *
     * @param position
     */
    public static void removeElement(int position){
        value.remove(position);
    }

    /**
     *
     * @param data
     */
    public static void removeElement(Ower data){
        value.remove(data);
    }

    /**
     *
     * @return
     */
    public static int getSize(){
       return value.size();
    }

    /**
     *
     * @return
     */
    public static ArrayList<Ower> getList(){
        return value;
    }

    /**
     *
     */
    public static void clearList(){
        value.clear();
    }
}
