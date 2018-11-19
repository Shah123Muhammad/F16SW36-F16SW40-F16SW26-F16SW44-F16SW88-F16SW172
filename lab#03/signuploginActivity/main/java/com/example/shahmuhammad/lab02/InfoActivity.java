package com.example.shahmuhammad.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {

     TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv=(TextView) findViewById(R.id.tv);
        Intent it=getIntent();

        String username=it.getStringExtra("Username");
        String password=it.getStringExtra("Password");

        tv.setText("Welcome "+username+" You have Logged successfully!!");
    }
}
