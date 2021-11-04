package com.vsb.kru13.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LevelMenu extends AppCompatActivity {

    ListView listView;
    DBHelper dbHelper;




    @Override
    protected void onRestart() {
        super.onRestart();
        createListView();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);

        createListView();

    }

    private void createListView() {
        //initializing listView
        listView = findViewById(R.id.listview);
        ArrayList<Level> levelArrayList = new ArrayList<>();
        dbHelper = new DBHelper(getApplicationContext());
        Cursor cursor = dbHelper.getData();

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                String highestScore = cursor.getString(cursor.getColumnIndex("highest_score"));

                levelArrayList.add(new Level(Integer.parseInt(id), Integer.parseInt(image), Integer.parseInt(highestScore)));

                cursor.moveToNext();
            }
        }
        cursor.close();

        LevelAdapter levelAdapter = new LevelAdapter(this, R.layout.list_row,levelArrayList);
        listView.setAdapter(levelAdapter);

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