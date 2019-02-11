package com.example.hp.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView elementList = findViewById(R.id.recyclerView);
        elementList.setLayoutManager(new LinearLayoutManager(this));
        //String[] data = {"element 0", "element 1", "element 2", "element 3", "element 4", "element 5", "element 6", "element 7", "element 8", "element 9", "element 10" };
        data = new ArrayList<String>();
        for(int i = 0; i<50; i++) {
            data.add("element "+i);
        }
        elementList.setAdapter(new ElementListAdapter(data,this));

    }
}
