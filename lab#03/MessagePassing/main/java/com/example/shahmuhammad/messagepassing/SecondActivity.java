package com.example.shahmuhammad.messagepassing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {


    EditText edtmessage;
    Button submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        edtmessage=(EditText)findViewById(R.id.edtmessage);
        submitbtn=(Button)findViewById(R.id.button2);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String message=edtmessage.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",message);
                setResult(1,intent);
                finish();//finishing activity
            }
        });
    }

}
