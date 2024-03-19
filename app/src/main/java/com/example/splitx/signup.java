package com.example.splitx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity implements View.OnClickListener {

    private TextView signup;
    private EditText name,email,password;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;  // Initialize Firebase Auth

    public signup() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mAuth=FirebaseAuth.getInstance();

        signup=(Button) findViewById(R.id.button4);
        signup.setOnClickListener(this);

        name=(EditText) findViewById(R.id.editTextTextPersonName2);
        email=(EditText) findViewById(R.id.editTextTextPersonName3);
        password=(EditText) findViewById(R.id.editTextTextPassword2);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);
    }



    public void login(View View){
        Intent intent2= new Intent(this,login.class);
        startActivity(intent2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4:
                signUp();
                break;

        }
    }

    private void signUp() {
        String name1=name.getText().toString().trim();
        String email1=email.getText().toString().trim();
        String password1=password.getText().toString().trim();

        if (name1.isEmpty()){
            name.setError("Name is Required");
            name.requestFocus();
            return;
        }

        if (email1.isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setError("Please Provide valid email");
            email.requestFocus();
            return;
        }

        if (password1.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (password1.length() < 6){
            password.setError("Min password length should be 6 characters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.GONE);
        mAuth.createUserWithEmailAndPassword(email1,password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                           User user=new User(name1,email1);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()){
                                            Toast.makeText(signup.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            // redirect to login layout
                                        }else {
                                            Toast.makeText(signup.this, "Failed to register, try again", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });

                        }
                        else {
                            Toast.makeText(signup.this, "Failed to register, try again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }
}