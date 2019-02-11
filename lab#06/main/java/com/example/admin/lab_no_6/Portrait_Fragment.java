package com.example.admin.lab_no_6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.lab_no_6.TASKTWO.Activity_B;

public class Portrait_Fragment extends Fragment {
    String[] dataArray = {"Peggy Whitson" , "Sally Ride", "Mae Jemison", "Judith Resnik" ,"Margaraet Rhea Seddon" ,"Kalpana Chawla" ,"Svetlana Yevgenyevna Savitskaya"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portrait_, container, false);
        final ListView listView = (ListView)view.findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity() ,android.R.layout.simple_list_item_1, dataArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity() , Activity_B.class));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
