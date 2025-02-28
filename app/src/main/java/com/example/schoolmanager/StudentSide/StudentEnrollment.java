package com.example.schoolmanager.StudentSide;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanager.Adapter.classContainerAdapter;
import com.example.schoolmanager.Database.AppDatabase;
import com.example.schoolmanager.Database.ClassList;
import com.example.schoolmanager.Database.Enrollment;
import com.example.schoolmanager.Database.EnrollmentDao;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;

import java.util.List;

public class StudentEnrollment extends AppCompatActivity {

    private RecyclerView classRecyclerView;
    private classContainerAdapter adapter;
    private EnrollmentDao enrollmentDao;
    private List<ClassList> classList;
    private int loggedInStudentID = 1; // Replace with actual logged-in student ID
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enrollment);

        classRecyclerView = findViewById(R.id.class_list_view);
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Fetch classes from database
        classList = MyApplication.getDatabase().classListDao().getAllClassList();

        // Set up adapter
        //adapter = new classContainerAdapter(classList, this::enrollStudent);
       // classRecyclerView.setAdapter(adapter);
    }

    private void enrollStudent(ClassList selectedClass) {
        if (enrollmentDao.checkEnrollment(loggedInStudentID, selectedClass.getClassID()) > 0) {
            showToast("You are already enrolled in this class!");
            return;
        }

        Enrollment enrollment = new Enrollment(loggedInStudentID, selectedClass.getClassID());
        enrollmentDao.enrollStudent(enrollment);

        showToast("Enrollment successful!");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
