package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView)findViewById(R.id.ListView);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(" ATBASH");
        arrayList.add(" CEASER CIPHER");
        arrayList.add(" VINEGER CIPHER");
        arrayList.add(" HILL");
        arrayList.add(" DES");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(view.getContext(), Main2Activity.class);
                    startActivity(intent);
                }
                    if (position == 0) {
                        Intent intent = new Intent(view.getContext(), Atbash.class);
                        startActivity(intent);
                    }
                    if(position==2){
                        Intent intent = new Intent(view.getContext(), ViginerCipher.class );
                        startActivity(intent);
                    }
                    if(position==4){
                        Intent intent = new Intent(view.getContext(),HillCipher.class);
                    startActivity(intent);

                }
                    if(position==5){
                        Intent intent = new Intent(view.getContext(),DESCipher.class);
                        startActivity(intent);
                    }

        };



    });
}}

