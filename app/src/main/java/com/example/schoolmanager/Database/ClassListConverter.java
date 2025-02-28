package com.example.schoolmanager.Database;

import androidx.room.TypeConverter;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class ClassListConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromFlashcardList(List<ClassList> classLists) {
        return gson.toJson(classLists);
    }

    @TypeConverter
    public static List<ClassList> toFlashcardList(String classLists) {
        Type listType = new TypeToken<List<ClassList>>() {}.getType();
        return gson.fromJson(classLists, listType);
    }
}
