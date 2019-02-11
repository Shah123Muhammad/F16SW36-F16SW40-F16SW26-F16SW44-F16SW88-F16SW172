package com.example.hp.toolbar;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private ListView listView;
    List<String> list;
    ArrayAdapter<String> arrayAdapter;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        listView = findViewById(R.id.listView);
        getSupportActionBar().setLogo(R.drawable.ic_search);
        list = new ArrayList<>();
        for(int i=2; i<=100; i+=2)
        {
            list.add("F16SW"+i);
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = listView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"User Clicked "+s, Toast.LENGTH_LONG).show();

            }
        });



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        position = info.position;
        getMenuInflater().inflate(R.menu.contextual_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                Toast.makeText(getApplicationContext(), "option 1", Toast.LENGTH_LONG).show();
            case R.id.delete:
                AlertDialog.Builder myAlert = new AlertDialog.Builder(MainActivity.this);
                myAlert.setTitle("Alert");
                myAlert.setMessage("Are you sure you want to delete this item?");
                myAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
                myAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_LONG).show();
                    }
                });
                myAlert.show();

            default:
                super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(),item.getTitle(),
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_info:
                Toast.makeText(getApplicationContext(),item.getTitle(),
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),item.getTitle(),
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
        //return super.onOptionsItemSelected(item);
    }


}

