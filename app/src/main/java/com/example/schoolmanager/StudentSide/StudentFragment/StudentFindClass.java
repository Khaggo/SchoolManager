package com.example.schoolmanager.StudentSide.StudentFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.schoolmanager.Activity.LoginActivity;
import com.example.schoolmanager.Activity.ViewClassroomActivity;
import com.example.schoolmanager.Adapter.classContainerAdapter;
import com.example.schoolmanager.Database.ClassList;
import com.example.schoolmanager.MainActivity;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;
import com.example.schoolmanager.StudentSide.StudentEnrollment;

import java.util.ArrayList;
import java.util.List;


public class StudentFindClass extends Fragment {


    List<ClassList> bucketClassList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewer =  inflater.inflate(R.layout.fragment_student_find_class, container, false);

        RecyclerView recyclerView = viewer.findViewById(R.id.student_find_class_recycler_view);
        Button checkoutButton = viewer.findViewById(R.id.fragment_student_find_class_check_out_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ClassList> classList = MyApplication.getDatabase().classListDao().getAllClassList();

        classContainerAdapter adapter = new classContainerAdapter(
                classList,
                (int position) -> {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Confirmation")
                            .setMessage("Do you want to add this to your bucket list")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                bucketClassList.add(MyApplication.getDatabase().classListDao().getClassListById(position));
                            })
                            .setNegativeButton("No", (dialog, which) -> {
                                dialog.dismiss();
                            })
                            .show();

                }
        );
        recyclerView.setAdapter(adapter);


        return viewer;
    }
}