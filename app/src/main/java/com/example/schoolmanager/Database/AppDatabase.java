package com.example.schoolmanager.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ClassList.class, SeatingArrangement.class, Student.class, Teacher.class, Enrollment.class}, version = 3, exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract ClassListDao classListDao();
    public abstract SeatDao seatDao();
    public abstract StudentTeacherDao studentTeacherDao();
    public abstract EnrollmentDao enrollmentDao(); // ✅ Add Enrollment DAO







}
