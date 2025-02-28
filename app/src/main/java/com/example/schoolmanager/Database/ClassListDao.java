package com.example.schoolmanager.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClassListDao {
    @Insert
    void insertClassList(ClassList classList);
    @Update
    void updateClassList(ClassList classList);

    @Query("SELECT * FROM class_list")
    List<ClassList> getAllClassList();

    @Query("SELECT * FROM class_list WHERE classID = :id")
    ClassList getClassListById(int id);

}
