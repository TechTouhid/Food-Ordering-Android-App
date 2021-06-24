package com.techtouhid.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.techtouhid.rms.database.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsername , loginPassword;
    private Button loginButton, signupHButton;
    private DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.loginusername);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginbutton);
        signupHButton = findViewById(R.id.signHbutton);

        myDb = new DataBaseHelper(this);

        loginUser();
        signupUser();

    }
    private void loginUser(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = myDb.checkUser(loginUsername.getText().toString() , loginPassword.getText().toString());
                if (var){
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void signupUser(){
        signupHButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , SignUpActivity.class));
            }
        });
    }

}