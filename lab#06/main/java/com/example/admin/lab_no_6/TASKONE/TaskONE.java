package com.example.admin.lab_no_6.TASKONE;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.lab_no_6.Landscape_Fragment;
import com.example.admin.lab_no_6.Portrait_Fragment;
import com.example.admin.lab_no_6.R;

public class TaskONE extends AppCompatActivity {
    private FragmentManager fm;
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_one);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.task_one_layout , new Portrait_Fragment());
        ft.commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_task_one);

        switch (newConfig.orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.task_one_layout , new Landscape_Fragment());
                ft.commit();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.task_one_layout , new Portrait_Fragment());
                ft.commit();
        }
        // TODO
    }
}
