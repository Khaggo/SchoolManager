package com.example.schoolmanager.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "class_list")
public class ClassList  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int classID;
    String subject;
    String teacher;
    String room;
    String date;
    String timeFrame;

    public ClassList(String subject, String teacher, String room, String date, String timeFrame) {
        this.subject = subject;
        this.teacher = teacher;
        this.room = room;
        this.date = date;
        this.timeFrame = timeFrame;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(String timeFrame) {
        this.timeFrame = timeFrame;
    }
}
