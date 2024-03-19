package com.example.splitx.helper;

import android.os.Build;

import com.example.splitx.models.Ower;
import com.example.splitx.models.Payable;
import com.example.splitx.models.PayableList;

import java.util.ArrayList;

public class Helper {
    public static interface PostTask{
        void run();
    }

    public static void compare(ArrayList<Ower> arr, PostTask runnable) {
        // comparator to sort the pair according to the second element

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            arr.sort((o1, o2) -> (int) (o1.getAmount() - o2.getAmount()));
        }else{
            int lowestOwerPosition = 0;
            Ower currentOwer;
            for(int i = 0; i < arr.size(); i++){
                currentOwer = arr.get(i);
                if(currentOwer.getAmount() < arr.get(lowestOwerPosition).getAmount()){
                    arr.set(i, arr.get(lowestOwerPosition));
                    arr.set(lowestOwerPosition, currentOwer);
                    lowestOwerPosition = i;
                }
            }
        }
        float sum=0f;
        for (int i = 0; i < arr.size(); i++) {
            sum = arr.get(i).getAmount() + sum;
        }
        float avg= sum / arr.size();

        for (int i = 0; i < arr.size(); i++) {
            Ower ow = arr.get(i);
            ow.setAmount(ow.getAmount() - avg);
        }
        float [][] pay = new float[arr.size()][arr.size()];
        int i = 0 , j = arr.size() - 1;
        while( i < j )
        {
            if( Math.abs( arr.get(i).getAmount()) >= arr.get(j).getAmount())
            {
                arr.get(i).setAmount(arr.get(i).getAmount() + arr.get(j).getAmount()) ;
                pay[i][j] = Math.abs(arr.get(j).getAmount());
                j--;
            }
            else if(Math.abs(arr.get(i).getAmount()) < arr.get(j).getAmount())
            {
                arr.get(j).setAmount(arr.get(j).getAmount() + arr.get(i).getAmount());
                pay[i][j] = Math.abs(arr.get(i).getAmount());
                i++;
            }
        }

        Payable payable;
        for(int c = 0 ; c < arr.size() ; c ++)
        {
            payable = new Payable();
            payable.setName(arr.get(c).getName());
            for(int d = 0 ; d < arr.size() ; d ++)
            {
                if(pay[c][d] != 0)
                    payable.addOwer(arr.get(d).getName(), pay[c][d]);
            }
            PayableList.addElement(payable);
        }
        runnable.run();
    }
}
