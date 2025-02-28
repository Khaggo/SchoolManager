package com.example.schoolmanager.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.annotation.Nullable;

@Entity(tableName = "seating_arrangement",
        foreignKeys = {
                @ForeignKey(entity = ClassList.class,
                        parentColumns = "classID",
                        childColumns = "classID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Student.class,
                        parentColumns = "studentID",
                        childColumns = "studentID",
                        onDelete = ForeignKey.SET_NULL) // If student is deleted, seat remains empty
        })
public class SeatingArrangement {
    @PrimaryKey(autoGenerate = true)
    int seatID;
    int classID; // Links to the class
    int seatNumber;
    @Nullable
    Integer studentID; // Nullable: NULL means unoccupied

    public SeatingArrangement(int classID, int seatNumber, @Nullable Integer studentID) {
        this.classID = classID;
        this.seatNumber = seatNumber;
        this.studentID = studentID;
    }

    public int getSeatID() { return seatID; }
    public int getClassID() { return classID; }
    public int getSeatNumber() { return seatNumber; }
    public Integer getStudentID() { return studentID; }

    public void setSeatID(int seatID) { this.seatID = seatID; }
    public void setClassID(int classID) { this.classID = classID; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public void setStudentID(@Nullable Integer studentID) { this.studentID = studentID; }
}

