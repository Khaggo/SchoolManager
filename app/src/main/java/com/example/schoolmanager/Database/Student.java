package com.example.schoolmanager.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "student_table")
public class Student {

    @PrimaryKey(autoGenerate = true)
    int studentID;
    String studentName;
    String studentUsername;
    int studentNumber;
    String studentPassword;

    List<ClassList> registeredClasses;

    public Student(String studentName, String studentUsername, int studentNumber, String studentPassword) {
        this.studentName = studentName;
        this.studentUsername = studentUsername;
        this.studentNumber = studentNumber;
        this.studentPassword = studentPassword;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public List<ClassList> getRegisteredClasses() {
        return registeredClasses;
    }

    public void setRegisteredClasses(List<ClassList> registeredClasses) {
        this.registeredClasses = registeredClasses;
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
