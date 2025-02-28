package com.example.schoolmanager.Database;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SeatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSeat(SeatingArrangement seat);


    @Query("SELECT * FROM seating_arrangement WHERE studentID = :studentID")
    List<SeatingArrangement> getSeatsForStudent(int studentID);

    @Query("SELECT * FROM student_table WHERE studentID = :studentID")
    Student getStudentById(int studentID);

    @Query("SELECT * FROM seating_arrangement WHERE classID = :classID")
    List<SeatingArrangement> getSeatsForClass(int classID);

    @Query("UPDATE seating_arrangement SET studentID = :studentID WHERE seatID = :seatID")
    void assignStudentToSeat(int seatID, @Nullable Integer studentID);

    @Query("UPDATE seating_arrangement SET studentID = NULL WHERE studentID = :studentID")
    void removeStudentFromSeat(int studentID);
}
