package com.example.admin.lab_no_6.TASKTWO;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.lab_no_6.Landscape_Fragment;
import com.example.admin.lab_no_6.Portrait_Fragment;
import com.example.admin.lab_no_6.R;
import android.os.Bundle;

public class Activity_A extends AppCompatActivity {
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_two);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_task_two);

        switch (newConfig.orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.layout_a , new Portrait_Fragment());
                ft.replace(R.id.layout_b , new Landscape_Fragment());
                ft.commit();
        }
        // TODO
    }
}
