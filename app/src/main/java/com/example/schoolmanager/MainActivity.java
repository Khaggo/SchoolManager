package com.example.schoolmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.schoolmanager.Activity.LoginActivity;
import com.example.schoolmanager.Database.Teacher;
import com.example.schoolmanager.Fragments.ClassListFragment;
import com.example.schoolmanager.Fragments.CreateClassFragment;
import com.example.schoolmanager.StudentSide.StudentMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;
   static Teacher teacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        instance = this;

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you want to log out?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            teacher = null;
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();

            }
        });

        BottomNavigationView navBar = findViewById(R.id.main_bottom_nav_bar);

        teacher = MyApplication.getDatabase().studentTeacherDao().getTeacherByUsername(getIntent().getStringExtra("username"));

        navBar.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.classList) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();

                fragmentManager1.beginTransaction().replace(R.id.main_frame_container, ClassListFragment.class, null).setReorderingAllowed(true).addToBackStack(null).commit();
                return true;
            } else if (item.getItemId() == R.id.createClass) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.main_frame_container, CreateClassFragment.class, null).setReorderingAllowed(true).addToBackStack(null).commit();
                return true;
            }


            return false;

        });
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public static Teacher getTeacher() {
        return teacher;
    }
}