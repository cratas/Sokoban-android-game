package com.vsb.kru13.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;



public class MainActivity extends AppCompatActivity {

    SokoView sokoView;
    int maxLevelWidth;
    int maxLevelHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int levelNum = getIntent().getIntExtra("levelNum", 1);

        sokoView = findViewById(R.id.sokoView);


        AssetManager assetManager = getAssets();
        InputStream input;
        try {
            input = assetManager.open("testing_levels.txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            //Levels string from .txt file
            String text = new String(buffer);

            //parsing .txt file from assets
            maxLevelWidth = getColumnsCount(text);
            String[] levels = makeStringArrayAndFillRows(text);
            maxLevelHeight = getRowsCount(levels);
            levels = fillColumns(levels);

            int[][] intLevels = new int[levels.length][];
            for(int i = 0; i < levels.length;i++) {
                intLevels[i] = convertToIntArray(levels[i]);
            }

            sokoView.setAllLevels(intLevels, 13, 11);
            startNewLevel(levelNum, levels);

        } catch(IOException e) {
            e.printStackTrace();
        }


    }

    private void startNewLevel(int levelNumber, String[] levels) {
        int[] level = convertToIntArray(levels[levelNumber]);
        sokoView.setLevel(level, levelNumber);
    }

    private String[] makeStringArrayAndFillRows(String oldString) {
        //replacing symbols
        oldString = oldString
                .replace(' ', '0')
                .replace('#', '1')
                .replace('$', '2')
                .replace('.', '3')
                .replace('@', '4')
                .replace('*','5');

        //split string to lines
        String[] lines = oldString.split("\r\n|\r|\n");
        String finalString = "";

        //filling empty space to rows
        for(int i = 0; i < lines.length; i++) {
            int countOfChars = lines[i].length();
            int remainingChars = maxLevelWidth - countOfChars;

            //'0'*(count of remaining chars)
            String emptySpace = new String(new char[remainingChars]).replace("\0", "0");
            finalString = finalString + (lines[i] + emptySpace +  "\n");
        }

        //returning string array
        return finalString.split("0000000000000\n");
    }

    private String[] fillColumns(String[] stringArray) {
        for(int i = 0; i < stringArray.length;i++) {
            String emptySpace = "";
            int countOfLines = stringArray[i].split("\n").length;

            for(int j = 0; j < stringArray[i].length();j++) {
                int remainingLines = maxLevelHeight - countOfLines;

                String emptyLine = new String(new char[maxLevelWidth]).replace("\0", "0");
                emptyLine+='\n';
                emptySpace = new String(new char[remainingLines]).replace("\0", emptyLine);
            }
                stringArray[i]+=emptySpace;
        }

        return stringArray;
    }

    private int[] convertToIntArray(String levelString) {
        int[] levelIntArray = new int[maxLevelWidth*maxLevelHeight];
        levelString = levelString.replace("\n", "");

        for(int i=0; i<levelString.length(); i++) {
            levelIntArray[i] = Character.digit(levelString.charAt(i), 10);
        }

        return levelIntArray;
    }

    private int getRowsCount(String[] levels) {
        int currentMax = -1;
        int max = -1;

        for(String x : levels) {
            for(int i = 0; i <x.length(); i++) {
                if(x.charAt(i) == '\n' ) {
                    currentMax++;
                }
            }
            if(currentMax > max) {
                max = currentMax;
            }
            currentMax = 0;
        }
        return  max;
    }

    private int getColumnsCount(String levelsString){
        int max = -1;
        int currentMax = -1;

        for(int i = 0; i < levelsString.length(); i++) {
            if(levelsString.charAt(i) == '\n') {
                if(currentMax > max) {
                    max = currentMax;
                }
                currentMax = 0;
            }
            currentMax++;
        }

        return  max-1; //-1 because of /r on the of line
    }
}
