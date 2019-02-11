package com.example.hp.bluetoothapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button turnOnBtn,getVisibleBtn,showPairedBtn,turnOffBtn;
    private ListView listView;
    private BluetoothAdapter ba;
    private Set<BluetoothDevice> pairedDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnOnBtn=(Button) findViewById(R.id.turnOnBtn);
        getVisibleBtn=(Button) findViewById(R.id.getVisibleBtn);
        showPairedBtn=(Button) findViewById(R.id.showPairedDeviceBtn);
        turnOffBtn=(Button) findViewById(R.id.turnOffBtn);
        listView=(ListView) findViewById(R.id.listView);

        ba=BluetoothAdapter.getDefaultAdapter();

        turnOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent,0);
            }
        });

        getVisibleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(intent,0);
            }
        });

        showPairedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pairedDevice=ba.getBondedDevices();

                ArrayList<String> list=new ArrayList<String>();
                for(BluetoothDevice b: pairedDevice){
                    list.add(b.getName());
                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(arrayAdapter);

            }
        });
        turnOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ba.isEnabled()){
                    ba.disable();
                }
            }
        });
    }
}
