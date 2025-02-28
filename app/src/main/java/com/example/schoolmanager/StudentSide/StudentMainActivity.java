package com.example.schoolmanager.StudentSide;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.schoolmanager.Activity.LoginActivity;
import com.example.schoolmanager.Database.Student;
import com.example.schoolmanager.Database.Teacher;
import com.example.schoolmanager.Fragments.ClassListFragment;
import com.example.schoolmanager.Fragments.CreateClassFragment;
import com.example.schoolmanager.MainActivity;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;
import com.example.schoolmanager.StudentSide.StudentFragment.StudentClassList;
import com.example.schoolmanager.StudentSide.StudentFragment.StudentFindClass;
import com.example.schoolmanager.StudentSide.StudentFragment.StudentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentMainActivity extends AppCompatActivity {

    public static StudentMainActivity instance;


    public static Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_main);
        instance = this;

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(StudentMainActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you want to log out?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            student = null;
                            Intent intent = new Intent(StudentMainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();

            }
        });


        BottomNavigationView navBar = findViewById(R.id.student_bottom_nav_bar);

        navBar.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.studentFindClass) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.student_main_frame, StudentFindClass.class, null).setReorderingAllowed(true).addToBackStack(null).commit();
                return true;
            } else if (item.getItemId() == R.id.studentClassList) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.student_main_frame, StudentClassList.class, null).setReorderingAllowed(true).addToBackStack(null).commit();
                return true;
            }else if (item.getItemId() == R.id.studentProfile) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.student_main_frame, StudentProfile.class, null).setReorderingAllowed(true).addToBackStack(null).commit();
                return true;
            }
            return false;

        });

        student = MyApplication.getDatabase().studentTeacherDao().getStudentByUsername(getIntent().getStringExtra("username"));
    }
    public static StudentMainActivity getInstance() {
        return instance;
    }

    public static Student getTeacher() {
        return student;
    }
}