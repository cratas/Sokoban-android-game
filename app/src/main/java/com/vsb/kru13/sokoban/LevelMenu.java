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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);

        //initializing listView
        listView = findViewById(R.id.listview);

        ArrayList<Level> levelArrayList = new ArrayList<>();

        dbHelper = new DBHelper(getApplicationContext());

//        Boolean isIN = dbHelper.insertLevelsData("0",Integer.toString(R.drawable.level1), "Level 1", "0");
//        if(isIN) Toast.makeText(getApplicationContext(), "jj", Toast.LENGTH_LONG).show();

        Cursor cursor = dbHelper.getData();

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String image = cursor.getString(cursor.getColumnIndex("image"));

                levelArrayList.add(new Level(Integer.parseInt(id), Integer.parseInt(image)));

                cursor.moveToNext();
            }
        }
        Toast.makeText(getApplicationContext(), Integer.toString(cursor.getCount()), Toast.LENGTH_LONG).show();
        cursor.close();

        LevelAdapter levelAdapter = new LevelAdapter(this, R.layout.list_row,levelArrayList);
        listView.setAdapter(levelAdapter);

//        levelArrayList.add(new Level(0, R.drawable.level1));
//        levelArrayList.add(new Level(1, R.drawable.level2));
//        levelArrayList.add(new Level(2, R.drawable.level3));
//        levelArrayList.add(new Level(3, R.drawable.level4));
//        levelArrayList.add(new Level(4, R.drawable.level5));
//        levelArrayList.add(new Level(5, R.drawable.level6));
//        levelArrayList.add(new Level(6, R.drawable.level7));
//        levelArrayList.add(new Level(7, R.drawable.level8));
//        levelArrayList.add(new Level(8, R.drawable.level9));
//        levelArrayList.add(new Level(9, R.drawable.level10));
//        levelArrayList.add(new Level(10, R.drawable.level11));
//        levelArrayList.add(new Level(11, R.drawable.level12));
//        levelArrayList.add(new Level(12, R.drawable.level13));
//        levelArrayList.add(new Level(13, R.drawable.level14));
//        levelArrayList.add(new Level(14, R.drawable.level15));



//        dbHelper = new DBHelper(this);
//
//        int counterForCheck = 0;
//        for(Level x : levelArrayList) {
//            String id = Integer.toString(x.getId());
//            String image = Integer.toString(x.getImage());
//            String name = x.getName();
//
//            Boolean isInserted = dbHelper.insertLevelsData(id, image, name, "0");
//            if(isInserted){
//                counterForCheck++;
//            }
//        }
//
//        Toast.makeText(getApplicationContext(), "Inserted" + Integer.toString(counterForCheck), Toast.LENGTH_LONG).show();


//        //arrayAdapter which have to set in listView
//        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(arrayAdapter);

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