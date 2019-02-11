package com.example.admin.lab_no_6;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.lab_no_6.TASKONE.TaskONE;
import com.example.admin.lab_no_6.TASKTWO.Activity_A;

public class MainActivity extends AppCompatActivity {
    private Button task_1;
    private Button task_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initials Buttons
        task_1 = findViewById(R.id.task_1);
        task_2 = findViewById(R.id.task_2);

        //Attach onClickListener To button one which will navigate to task 1
        task_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , TaskONE.class));
            }
        });

        //Attach onClickListener To button two which will navigate to task 2
        task_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , Activity_A.class));
            }
        });
    }

}
