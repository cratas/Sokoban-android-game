package com.vsb.kru13.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LevelMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);

        //initializing listView
        ListView listView = findViewById(R.id.listview);

        //adding "buttons" to listView
        List<String> list = new ArrayList<>();
        list.add("Level 1");
        list.add("Level 2");
        list.add("Level 3");
        list.add("Level 4");
        list.add("Level 5");
        list.add("Level 6");
        list.add("Level 7");
        list.add("Level 8");
        list.add("Level 9");
        list.add("Level 10");
        list.add("Level 11");
        list.add("Level 12");
        list.add("Level 13");
        list.add("Level 14");
        list.add("Level 15");


        //arrayAdapter which have to set in listView
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        //onCLick listener, allows to choose level to play
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    startActivitySetLevel(1);
                } else if(position == 1) {
                    startActivitySetLevel(2);
                } else if(position == 2) {
                    startActivitySetLevel(3);
                } else if(position == 3) {
                    startActivitySetLevel(4);
                } else if(position == 4) {
                    startActivitySetLevel(5);
                } else if(position == 5) {
                    startActivitySetLevel(6);
                } else if(position == 6) {
                    startActivitySetLevel(7);
                } else if(position == 7) {
                    startActivitySetLevel(8);
                } else if(position == 8) {
                    startActivitySetLevel(9);
                } else if(position == 9) {
                    startActivitySetLevel(10);
                } else if(position == 10) {
                    startActivitySetLevel(11);
                } else if(position == 11) {
                    startActivitySetLevel(12);
                } else if(position == 12) {
                    startActivitySetLevel(13);
                } else if(position == 13) {
                    startActivitySetLevel(14);
                } else if(position == 14) {
                    startActivitySetLevel(15);
                }
            }
        });
    }

    //starting new activity with chosen level
    private void startActivitySetLevel(int levelNumber) {
        Intent intent = new Intent(LevelMenu.this, MainActivity.class);
        intent.putExtra("levelNum", levelNumber);
        startActivity(intent);
    }
}