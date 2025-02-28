package com.example.schoolmanager.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolmanager.Activity.ViewClassroomActivity;
import com.example.schoolmanager.Adapter.classContainerAdapter;
import com.example.schoolmanager.Database.ClassList;
import com.example.schoolmanager.Database.SeatingArrangement;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;

import java.util.List;

public class ClassListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewer = inflater.inflate(R.layout.fragment_class_list, container, false);

        RecyclerView recyclerView = viewer.findViewById(R.id.fragment_class_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get class list from database
        List<ClassList> classList = MyApplication.getDatabase().classListDao().getAllClassList();

        classContainerAdapter adapter = new classContainerAdapter(
                classList,
                (classListChosen) -> {
                    Intent intent = new Intent(getContext(), ViewClassroomActivity.class);
                    intent.putExtra("class_id", classListChosen.getClassID());
                    startActivity(intent);
                }
        );

        recyclerView.setAdapter(adapter);

        return viewer;
    }
}
