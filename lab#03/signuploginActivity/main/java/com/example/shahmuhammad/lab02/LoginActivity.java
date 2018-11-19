package com.example.shahmuhammad.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

      EditText edtuname,edtpassword;
      Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        edtuname=(EditText) findViewById(R.id.edtuname);
        edtpassword=(EditText) findViewById(R.id.edtpassword);
        btnlogin=(Button) findViewById(R.id.loginbtn);



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(edtuname.getText().toString(),edtpassword.getText().toString());
            }
        });

    }
    private void validate(String Username,String Userpassword){
           Intent intent=getIntent();
        if((Username.equals(intent.getStringExtra("Name")))&& (Userpassword.equals(intent.getStringExtra("Password")))){

            Intent it=new Intent(LoginActivity.this, InfoActivity.class);
            it.putExtra("Username",Username);
            it.putExtra("Password",Userpassword);
            startActivity(it);
        }else{
            Toast.makeText(getApplication(),"Invalid email or password",Toast.LENGTH_LONG).show();
        }

    }

}
