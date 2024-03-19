package com.example.splitx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splitx.adapters.ListInputAdapter;
import com.example.splitx.helper.Helper;
import com.example.splitx.models.Ower;
import com.example.splitx.models.OwerList;
import com.example.splitx.models.Payable;
import com.example.splitx.models.PayableList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class OwerListActivity extends AppCompatActivity {

    private FloatingActionButton doneBtn;
    private FloatingActionButton addBtn;
    private RecyclerView owerListRecycler;
    private ListInputAdapter adapter = new ListInputAdapter();
    private TextView total_amount_view;
    private TextView list_total_amount_text;

    private float totalAmount = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ower_list);

        Toast.makeText(this, getIntent().getStringExtra("TAG"), Toast.LENGTH_LONG).show();

        addBtn = findViewById(R.id.add_button);
        doneBtn=findViewById(R.id.submit_button);
        owerListRecycler=findViewById(R.id.recyclerView);
        list_total_amount_text=findViewById(R.id.list_total_amount_text);
        total_amount_view=findViewById(R.id.total_amount_view);

    }

    @Override
    protected void onStart() {
        super.onStart();

        OwerList.clearList();
        PayableList.clearList();

        addBtn.setOnClickListener(v->{
            OwerList.addElement(new Ower("", 0));
            updateRecyclerData(OwerList.getSize());
        });

        doneBtn.setOnClickListener(v->{
            ArrayList<Ower> value = OwerList.getList();
            for (int i = 0; i < value.size(); i++){
                totalAmount += value.get(i).getAmount();
            }
            Helper.compare(value, () -> {
                for(int i = 0; i < PayableList.getList().size(); i++){
                    Payable p = PayableList.get(i);
                    Log.d("Testing", p.getName() + " will pay " + p.getOwedList().toString());
                }
                Intent intent = new Intent(this, PayableActivity.class);
                intent.putExtra("payableList", PayableList.getList());
                startActivity(intent);

                Intent intent1=new Intent(this,PayableActivity.class);
                intent.putExtra("totalAmount",totalAmount);
                startActivity(intent1);


                finish();
            });


            Toast.makeText(this, "total amount = "+totalAmount, Toast.LENGTH_SHORT).show();
            list_total_amount_text.setText(Float.toString(totalAmount));
            totalAmount=0f;
            // TODO: {Set total amount using the variable 'totalAmount'}
        });

        initRecycler();
    }

    /**
     * Inits the adapter and attaches it to the recycler view
     */
    private void initRecycler(){
        owerListRecycler.setHasFixedSize(true);
        owerListRecycler.setLayoutManager(new LinearLayoutManager(this));
        owerListRecycler.setAdapter(adapter);
    }


    /**
     * Notifies the adapter when a new element is added
     * @param pos
     */
    private void updateRecyclerData(int pos){
        adapter.notifyItemInserted(pos);
    }
}