package com.example.schoolmanager.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = {ClassList.class, SeatingArrangement.class, Student.class, Teacher.class, Enrollment.class}, version = 4, exportSchema = false)
@TypeConverters(ClassListConverter.class)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract ClassListDao classListDao();
    public abstract SeatDao seatDao();
    public abstract StudentTeacherDao studentTeacherDao();
    public abstract EnrollmentDao enrollmentDao(); // ✅ Add Enrollment DAO







}
