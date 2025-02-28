package com.example.schoolmanager.StudentSide.StudentFragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolmanager.Adapter.classContainerAdapter;
import com.example.schoolmanager.Database.ClassList;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;
import com.example.schoolmanager.StudentSide.StudentMainActivity;

import java.util.List;


public class StudentClassList extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewer = inflater.inflate(R.layout.fragment_student_class_list, container, false);
        RecyclerView recyclerView = viewer.findViewById(R.id.fragment_student_class_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ClassList> classListRegistered = StudentMainActivity.student.getRegisteredClasses();

        classContainerAdapter adapter = new classContainerAdapter(
                classListRegistered,
                (ClassList classListChosen) -> {



                }
        );
        recyclerView.setAdapter(adapter);




        return viewer;
    }
}