package com.example.splitx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.splitx.models.Payable;

import java.util.ArrayList;

public class PayableActivity extends AppCompatActivity {

    private TextView tx;
    private TextView tx2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payable);

        tx = findViewById(R.id.payable_activity_text);
        tx2=findViewById(R.id.txt_View_total);
        String total=getIntent().getStringExtra("Total Amount");
        tx2.setText(total);
        String bill=getIntent().getStringExtra("billName");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = this.getIntent();

        ArrayList<Payable> list = (ArrayList<Payable>) intent.getSerializableExtra("payableList");

        tx.setMovementMethod(new ScrollingMovementMethod());

        StringBuilder message = new StringBuilder("Here is the ower list of the bill name");

        for(int i = 0; i < list.size(); i++){
           message.append("\n\n").append(list.get(i).getName()).append(", will give to :");
           for (String ower: list.get(i).getOwedList().keySet()){
               if(list.get(i).getOwedList().get(ower) > 0f){
                   message.append("\n").append(ower).append(" -> ").append(list.get(i).getOwedList().get(ower));
               }
           }
           message.append("\n--------------\n");
        }

        tx.setText(message.toString());

    }
}