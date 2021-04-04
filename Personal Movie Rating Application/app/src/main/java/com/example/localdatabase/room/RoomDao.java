package com.example.localdatabase.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.localdatabase.model.ModelImdb;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSingleData(ModelImdb imdb);

    @Update
    void updateSingleData(ModelImdb imdb);

    /*@Delete
    void DeleteSingleData(Double id);*/

    @Query("DELETE FROM ratings WHERE id=:id")
    void deleteSingleData(Double id);

    @Query("SELECT * FROM ratings ORDER BY id DESC")
    LiveData<List<ModelImdb>> getAllData();



}
