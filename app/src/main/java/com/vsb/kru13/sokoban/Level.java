package com.vsb.kru13.sokoban;

public class Level {
    int id;
    int image;
    String name;
    int highestScore;

    public Level(int id, int image, int highestScore) {
        this.id = id;
        this.image = image;
        this.name = "Level " +  Integer.toString(id + 1);
        this.highestScore = highestScore;
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
