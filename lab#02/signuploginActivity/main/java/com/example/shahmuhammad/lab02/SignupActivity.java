package com.example.shahmuhammad.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SignupActivity extends AppCompatActivity {
    EditText edtnm,edtmail,edtpwd,edtdte;
    RadioButton rdb1,rdb2;
    Button signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtnm=(EditText) findViewById(R.id.edtname);
        edtmail=(EditText) findViewById(R.id.edtemail);
        edtpwd=(EditText) findViewById(R.id.edtpassword);
        edtdte=(EditText) findViewById(R.id.edtdate);
        rdb1=(RadioButton) findViewById(R.id.radioButton1);
        rdb2=(RadioButton) findViewById(R.id.radioButton2);
        signupbtn=(Button) findViewById(R.id.button);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtnm.getText().toString();
                String email=edtmail.getText().toString();
                String password=edtpwd.getText().toString();
                String date=edtdte.getText().toString();


                 Intent it=new Intent(SignupActivity.this,LoginActivity.class);

                it.putExtra("Name",name);
                 it.putExtra("Email",email);
                 it.putExtra("Password",password);
                 it.putExtra("D-O-B",date);

                 startActivity(it);
            }
        });
    }
}
