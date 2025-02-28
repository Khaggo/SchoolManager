package com.example.schoolmanager.StudentSide.StudentFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.schoolmanager.Database.AppDatabase;
import com.example.schoolmanager.Database.Student;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;
import com.example.schoolmanager.StudentSide.StudentMainActivity;

public class StudentProfile extends Fragment {

    private EditText nameEditText, studentNumberEditText, usernameEditText, passwordEditText;
    private Button saveButton;

    private int loggedInStudentID =StudentMainActivity.student.getStudentID(); // Replace with actual logged-in student ID

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_profile, container, false);

        // Initialize Views
        nameEditText = view.findViewById(R.id.profile_name);
        studentNumberEditText = view.findViewById(R.id.profile_student_number);
        usernameEditText = view.findViewById(R.id.profile_username);
        passwordEditText = view.findViewById(R.id.profile_password);
        saveButton = view.findViewById(R.id.profile_save_button);

        // Get database instance


        // Load Student Data
        loadStudentData();

        // Save Button Click Listener
        saveButton.setOnClickListener(v -> saveChanges());

        return view;
    }

    private void loadStudentData() {
        // Fetch student from database
        Student student = StudentMainActivity.student;

        if (student != null) {
            nameEditText.setText(student.getStudentName());
            studentNumberEditText.setText(String.valueOf(student.getStudentNumber()));
            usernameEditText.setText(student.getStudentUsername());
            passwordEditText.setText(student.getStudentPassword());
        } else {
            Toast.makeText(getContext(), "Error: Student not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveChanges() {
        String newName = nameEditText.getText().toString().trim();
        String newPassword = passwordEditText.getText().toString().trim();

        // Validation
        if (newName.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(getContext(), "Name and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newPassword.length() < 8) {
            Toast.makeText(getContext(), "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update database
        MyApplication.getDatabase().studentTeacherDao().updateStudentProfile(loggedInStudentID, newName, newPassword);
        Toast.makeText(getContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
    }
}
