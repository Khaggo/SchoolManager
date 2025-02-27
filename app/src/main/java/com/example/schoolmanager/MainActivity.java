package com.example.schoolmanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.schoolmanager.Fragments.ClassListFragment;
import com.example.schoolmanager.Fragments.CreateClassFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView navBar = findViewById(R.id.main_bottom_nav_bar);

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
}