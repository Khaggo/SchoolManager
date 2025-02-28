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
import com.example.schoolmanager.StudentSide.StudentActivity.ClassCheckoutActivity;
import com.example.schoolmanager.StudentSide.StudentEnrollment;
import com.example.schoolmanager.StudentSide.StudentMainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class StudentFindClass extends Fragment {


    List<ClassList> bucketClassList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewer = inflater.inflate(R.layout.fragment_student_find_class, container, false);

        RecyclerView recyclerView = viewer.findViewById(R.id.student_find_class_recycler_view);
        Button checkoutButton = viewer.findViewById(R.id.fragment_student_find_class_check_out_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ClassList> classList = MyApplication.getDatabase().classListDao().getAllClassList();
        List<ClassList> InitialClassListRegistered = StudentMainActivity.student.getRegisteredClasses();
        List<ClassList> classListRegistered;

        if(InitialClassListRegistered == null) {
            classListRegistered = new ArrayList<>();
        } else {
            classListRegistered =  StudentMainActivity.student.getRegisteredClasses();
        }



        classContainerAdapter adapter = new classContainerAdapter(
                classList,
                (ClassList classListChosen) -> {
                    ClassList chosen = MyApplication.getDatabase().classListDao().getClassListById(classListChosen.getClassID());

                    // ✅ Check if the class is already in the bucket list
                    for (ClassList c : bucketClassList) {
                        if (c.getClassID() == chosen.getClassID()) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Error")
                                    .setMessage("This class is already in your bucket list.")
                                    .show();
                            return;
                        }
                    }

                    // ✅ Check if the class is already registered
                    for (ClassList c : classListRegistered) {
                        if (c.getClassID() == chosen.getClassID()) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Error")
                                    .setMessage("You are already registered for this class.")
                                    .show();
                            return;
                        }
                    }

                    // ✅ Check for time conflicts (overlapping time slots)
                    for (ClassList c : bucketClassList) {
                        if (isTimeSlotClashing(c.getTimeFrame(), chosen.getTimeFrame())) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Error")
                                    .setMessage("Time conflict detected! You are already registered for a class during this time.")
                                    .show();
                            return;
                        }
                    }
                    for (ClassList c : classListRegistered) {
                        if (isTimeSlotClashing(c.getTimeFrame(), chosen.getTimeFrame())) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Error")
                                    .setMessage("Time conflict detected! You are already registered for a class during this time.")
                                    .show();
                            return;
                        }
                    }

                    // ✅ If no conflicts, add to bucket list
                    new AlertDialog.Builder(getContext())
                            .setTitle("Confirmation")
                            .setMessage("Do you want to add this to your bucket list?")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                bucketClassList.add(chosen);
                            })
                            .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                            .show();
                }
        );

        recyclerView.setAdapter(adapter);

        checkoutButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ClassCheckoutActivity.class);
            intent.putExtra("bucketList", (ArrayList<ClassList>) bucketClassList);
            intent.putExtra("studentID", StudentMainActivity.student.getStudentID());
            startActivity(intent);
        });


        return viewer;
    }
    private boolean isTimeSlotClashing(String time1, String time2) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        try {
            // ✅ Extract start and end times
            String[] time1Parts = time1.split("-");
            String[] time2Parts = time2.split("-");

            Date start1 = format.parse(time1Parts[0].trim());
            Date end1 = format.parse(time1Parts[1].trim());
            Date start2 = format.parse(time2Parts[0].trim());
            Date end2 = format.parse(time2Parts[1].trim());

            // ✅ Check if the time slots overlap
            return (start1.before(end2) && start2.before(end1));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}