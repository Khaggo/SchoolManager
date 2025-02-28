package com.example.schoolmanager.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {

    @PrimaryKey(autoGenerate = true)
    int studentID;
    String studentName;
    String studentUsername;
    int studentNumber;
    String studentPassword;

    public Student(String studentName, String studentUsername, int studentNumber, String studentPassword) {
        this.studentName = studentName;
        this.studentUsername = studentUsername;
        this.studentNumber = studentNumber;
        this.studentPassword = studentPassword;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}
