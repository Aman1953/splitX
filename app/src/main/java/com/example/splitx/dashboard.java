package com.example.splitx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {

    private Button nextBtn;
    private TextView logout;
    private FirebaseAuth mAuth;
    private  EditText billname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        billname=(EditText)findViewById(R.id.editTextTextPersonName4);
        nextBtn = findViewById(R.id.dash_next_btn);


    }


    @Override
    protected void onStart() {
        super.onStart();

        logout = findViewById(R.id.textView11);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(dashboard.this,login.class);
                startActivity(intent);
                finish();
                Toast.makeText(dashboard.this, "Successfully Logged out", Toast.LENGTH_SHORT).show();
            }
        });



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(billname.getText().toString())){

                    billname.setError("Bill Name is compulsary");
                    return;
                }
                nextBtn.setOnClickListener(view->{

                    Intent intent1=new Intent(dashboard.this,PayableActivity.class);
                    intent1.putExtra("billName", (Parcelable) billname);
                    startActivity(intent1);


                    Intent intent = new Intent(dashboard.this,OwerListActivity.class);
                    intent.putExtra("TAG", "Add and Split!");
                    startActivity(intent);



                });
            }
        });

    }


}
