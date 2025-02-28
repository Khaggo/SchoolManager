package com.example.schoolmanager.Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "enrollment_table",
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "studentID",
                        childColumns = "studentID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = ClassList.class,
                        parentColumns = "classID",
                        childColumns = "classID",
                        onDelete = ForeignKey.CASCADE)
        })
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    int enrollmentID;
    int studentID;
    int classID;

    public Enrollment(int studentID, int classID) {
        this.studentID = studentID;
        this.classID = classID;
    }

    public int getEnrollmentID() { return enrollmentID; }
    public int getStudentID() { return studentID; }
    public int getClassID() { return classID; }

    public void setEnrollmentID(int enrollmentID) { this.enrollmentID = enrollmentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public void setClassID(int classID) { this.classID = classID; }
}
