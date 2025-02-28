package com.example.schoolmanager.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface StudentTeacherDao {

    @Query("SELECT COUNT(*) FROM student_table WHERE studentUsername = :username AND studentPassword = :password")
    int checkStudentLogin(String username, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

    @Query("SELECT COUNT(*) FROM student_table WHERE studentUsername = :username")
    int checkIfUsernameExists(String username);

    @Query("SELECT COUNT(*) FROM student_table")
    int getStudentCount();

    // ✅ Insert a new teacher
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeacher(Teacher teacher);

    // ✅ Check login credentials
    @Query("SELECT COUNT(*) FROM teacher_table WHERE teacherUsername = :username AND teacherPassword = :password")
    int checkTeacherLogin(String username, String password);

    // ✅ Check if a username already exists
    @Query("SELECT COUNT(*) FROM teacher_table WHERE teacherUsername = :username")
    int checkIfFacultyUsernameExists(String username);

    // ✅ Count the total number of teachers
    @Query("SELECT COUNT(*) FROM teacher_table")
    int getTeacherCount();

    // ✅ Get teacher details by username
    @Query("SELECT * FROM teacher_table WHERE teacherUsername = :username LIMIT 1")
    Teacher getTeacherByUsername(String username);

    // ✅ Get teacher details by ID
    @Query("SELECT * FROM teacher_table WHERE teacherID = :teacherID LIMIT 1")
    Teacher getTeacherById(int teacherID);
}
