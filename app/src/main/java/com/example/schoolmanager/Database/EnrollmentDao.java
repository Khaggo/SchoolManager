package com.example.schoolmanager.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface EnrollmentDao {
    // ✅ Enroll a student in a class
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void enrollStudent(Enrollment enrollment);

    // ✅ Check if a student is already enrolled in a class
    @Query("SELECT COUNT(*) FROM enrollment_table WHERE studentID = :studentID AND classID = :classID")
    int checkEnrollment(int studentID, int classID);

    // ✅ Get all classes a student is enrolled in
    @Query("SELECT classID FROM enrollment_table WHERE studentID = :studentID")
    List<Integer> getEnrolledClasses(int studentID);
}
