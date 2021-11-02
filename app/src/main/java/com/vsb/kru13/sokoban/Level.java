package com.vsb.kru13.sokoban;

public class Level {
    int id;
    int image;
    String name;
    String highestScoreString;
    int highestScore;

    public Level(int id, int image) {
        this.id = id;
        this.image = image;
        this.name = "Level " +  Integer.toString(id + 1);
        this.highestScoreString ="Higher score: 0";
        this.highestScore = 0;
    }

    public String getHighestScoreString() {
        return highestScoreString;
    }

    public void setHighestScoreString(String highestScoreString) {
        this.highestScoreString = highestScoreString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image= image;
    }
}
