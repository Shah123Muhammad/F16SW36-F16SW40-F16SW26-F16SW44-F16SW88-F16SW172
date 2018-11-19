package com.example.shahmuhammad.messagepassing;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView messagetv;
    Button msgbtn;
   // static final int REQUEST_CODE=1; we can use request code here as well
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messagetv=(TextView)findViewById(R.id.messagetv);
        msgbtn=(Button)findViewById(R.id.msgbtn);

        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            String message=data.getStringExtra("MESSAGE");
            messagetv.setText(message);
        }
    }


}
