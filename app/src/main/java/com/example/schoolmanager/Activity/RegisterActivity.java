package com.example.schoolmanager.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolmanager.Database.AppDatabase;
import com.example.schoolmanager.Database.Student;
import com.example.schoolmanager.Database.StudentTeacherDao;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullNameField, usernameField, passwordField, rePasswordField;
    private Button registerButton, returnButton;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;
        // Initialize Views
        fullNameField = findViewById(R.id.register_fullname_text_field);
        usernameField = findViewById(R.id.register_username_text_field);
        passwordField = findViewById(R.id.register_password_text_field);
        rePasswordField = findViewById(R.id.register_repassword_text_field);
        registerButton = findViewById(R.id.register_register_button);
        returnButton = findViewById(R.id.register_return_button);

        // Register Button Click
        registerButton.setOnClickListener(v -> registerUser());

        // Return Button Click
        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void registerUser() {
        String fullName = fullNameField.getText().toString().trim();
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String rePassword = rePasswordField.getText().toString().trim();

        // Validation Checks
        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            showToast("All fields are required!");
            return;
        }

        if (username.length() < 8) {
            showToast("Username must be at least 8 characters long!");
            return;
        }

        if (password.length() < 8) {
            showToast("Password must be at least 8 characters long!");
            return;
        }

        if (!password.equals(rePassword)) {
            showToast("Passwords do not match!");
            return;
        }

        if (MyApplication.getDatabase().studentTeacherDao().checkIfUsernameExists(username) > 0) {
            showToast("Username is already taken!");
            return;
        }

        // Generate Student Number: YYYY0000X
        int studentCount = MyApplication.getDatabase().studentTeacherDao().getStudentCount() + 1;
        String studentNumber = generateStudentNumber(studentCount);

        // Insert Student into Database
        Student newStudent = new Student(fullName, username, Integer.parseInt(studentNumber), password);
        MyApplication.getDatabase().studentTeacherDao().insertStudent(newStudent);

        showToast("Registration successful! Your Student Number: " + studentNumber);

        // Redirect to Login
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private String generateStudentNumber(int studentCount) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear + "0000" + studentCount;
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
