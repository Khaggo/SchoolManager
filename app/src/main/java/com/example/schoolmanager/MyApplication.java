package com.example.schoolmanager;

import android.app.Application;
import android.media.MediaPlayer;

import androidx.room.Room;

import com.example.schoolmanager.Database.AppDatabase;
import com.example.schoolmanager.Database.Teacher;

public class MyApplication extends Application {
    private static MyApplication instance;
    private static AppDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // Initialize Room Database
        database = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "AppDatabase-db"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
//
//        MyApplication.getDatabase().studentTeacherDao().insertTeacher(new Teacher(
//                "admin",
//                "admin",
//                1,
//                "admin"
//        ));



    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
