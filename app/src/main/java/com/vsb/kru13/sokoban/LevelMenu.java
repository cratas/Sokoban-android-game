package com.vsb.kru13.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
    }

    public void startLevel1(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("levelNum", 1);
        startActivity(intent);
    }

    public void startLevel2(View view) {
//        view.getId() == R.id.button;
        Intent intent  = new Intent(this, MainActivity.class);
        intent.putExtra("levelNum", 2);
        startActivity(intent);
    }

    public void startLevel3(View view) {
        //        view.getId() == R.id.button;
        Intent intent  = new Intent(this, MainActivity.class);
        intent.putExtra("levelNum", 3);
        startActivity(intent);
    }

    public void startLevel4(View view) {
        //        view.getId() == R.id.button;
        Intent intent  = new Intent(this, MainActivity.class);
        intent.putExtra("levelNum", 4);
        startActivity(intent);
    }

    public void startLevel5(View view) {
        //        view.getId() == R.id.button;
        Intent intent  = new Intent(this, MainActivity.class);
        intent.putExtra("levelNum", 5);
        startActivity(intent);
    }
}