package com.example.schoolmanager.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolmanager.MainActivity;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;
import com.example.schoolmanager.StudentSide.StudentMainActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private RadioGroup userTypeGroup;
    private RadioButton studentRadioButton, facultyRadioButton;
    private Button loginButton, registerButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        // Initialize UI Elements
        usernameField = findViewById(R.id.login_username_text_field);
        passwordField = findViewById(R.id.login_password_text_field);
        userTypeGroup = findViewById(R.id.login_radio_group);
        studentRadioButton = findViewById(R.id.login_student_radio_button);
        facultyRadioButton = findViewById(R.id.login_faculty_radio_button);
        loginButton = findViewById(R.id.login_login_button);
        registerButton = findViewById(R.id.login_register_button);

        // Login Button Click
        loginButton.setOnClickListener(v -> loginUser());

        // Register Button Click
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        // Validate Input Fields
        if (username.isEmpty() || password.isEmpty()) {
            showToast("Please enter username and password!");
            return;
        }

        // Validate User Type Selection
        int selectedId = userTypeGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            showToast("Please select Student or Faculty!");
            return;
        }

        boolean isStudent = (selectedId == R.id.login_student_radio_button);
        boolean isFaculty = (selectedId == R.id.login_faculty_radio_button);

        if (isStudent) {
            if (MyApplication.getDatabase().studentTeacherDao().checkStudentLogin(username, password) > 0) {
                showToast("Login Successful as Student!");
                Intent intent = new Intent(LoginActivity.this, StudentMainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else {
                showToast("Invalid Student Credentials!");
            }
        } else if (isFaculty) {
            if (MyApplication.getDatabase().studentTeacherDao().checkTeacherLogin(username, password) > 0) {
                showToast("Login Successful as Faculty!");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else {
                showToast("Invalid Faculty Credentials!");
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
