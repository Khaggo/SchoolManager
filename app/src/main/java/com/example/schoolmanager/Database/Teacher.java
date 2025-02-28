package com.example.schoolmanager.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teacher_table")
public class Teacher {
    @PrimaryKey(autoGenerate = true)
    int teacherID;

    String teacherName;
    String teacherUsername;
    int teacherNumber;

    String teacherPassword;


    public Teacher(String teacherName, String teacherUsername, int teacherNumber, String teacherPassword) {
        this.teacherName = teacherName;
        this.teacherUsername = teacherUsername;
        this.teacherNumber = teacherNumber;
        this.teacherPassword = teacherPassword;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public int getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(int teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }
}
