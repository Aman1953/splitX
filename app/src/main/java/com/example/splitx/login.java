package com.example.splitx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener{

    private EditText editEmail,editPassword;
    private Button logIn;
    private TextView ForgetTextLink;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        logIn=(Button) findViewById(R.id.button);
        ForgetTextLink = findViewById(R.id.textView4);
        editEmail=(EditText) findViewById(R.id.editTextTextPersonName);
        editPassword=(EditText) findViewById(R.id.editTextTextPassword);

        progressBar = findViewById(R.id.progressBar);

        mAuth=FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();
        logIn.setOnClickListener(it -> {
            userLogin();
        });

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(this, dashboard.class));
            finish();
        }
    }

    public void signup(View View){
        Intent intent=new Intent(this,signup.class);
        startActivity(intent);
    }


    /*
    public void dashboard(View View){
        Intent intent3=new Intent(this,dashboard.class);
        startActivity(intent3);
    }*/

/*    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:
                userLogin();
                break;
        }

    }*/

    private void userLogin() {
        Log.d("error_trap", "I am called!");
        String email2=editEmail.getText().toString().trim();
        String password2=editPassword.getText().toString().trim();

        if (email2.isEmpty()){
            editEmail.setError("Email is Required");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email2).matches()){
            editEmail.setError("Please Provide valid email");
            editEmail.requestFocus();
            return;
        }

        if (password2.isEmpty()){
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }
        if (password2.length() < 6){
            editPassword.setError("Min password length should be 6 characters");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.GONE);

        mAuth.signInWithEmailAndPassword(email2,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    // redirect to user profile
                    startActivity(new Intent(login.this,dashboard.class));

                }else {
                    Toast.makeText(login.this, "Failed to login, Please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        ForgetTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email to Receive Reset Link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //extract the email and send reset link

                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(login.this, "Reset link sent to your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this, "ERROR ! Reset link is not sent"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });

    }
}