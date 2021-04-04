package com.example.localdatabase.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ratings")
public class ModelImdb {

    @PrimaryKey(autoGenerate = true)
    int id;
    int year;
    Float rating;
    String name;

    public ModelImdb(String name,Float rating,int year) {
        this.year = year;
        this.rating = rating;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
