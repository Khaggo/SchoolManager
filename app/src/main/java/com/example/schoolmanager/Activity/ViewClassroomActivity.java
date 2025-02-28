package com.example.schoolmanager.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanager.Adapter.SeatAdapter;
import com.example.schoolmanager.Database.AppDatabase;
import com.example.schoolmanager.Database.SeatingArrangement;
import com.example.schoolmanager.Database.Student;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;

import java.util.ArrayList;
import java.util.List;

public class ViewClassroomActivity extends AppCompatActivity {

    private int classID;
    private RecyclerView recyclerView;
    private SeatAdapter adapter;
    private List<SeatingArrangement> seats;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classroom);

        classID = getIntent().getIntExtra("class_id", -1);
        db = MyApplication.getDatabase();

        recyclerView = findViewById(R.id.view_classroom_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        loadSeats();
    }

    private void loadSeats() {
        seats = db.seatDao().getSeatsForClass(classID);

        if (seats.isEmpty()) {
            for (int i = 1; i <= 30; i++) {
                SeatingArrangement newSeat = new SeatingArrangement(classID, i, null);
                db.seatDao().insertSeat(newSeat);
            }
            seats = db.seatDao().getSeatsForClass(classID);
        }

        adapter = new SeatAdapter(seats, this, db.seatDao(), this::showStudentSelectionDialog);
        recyclerView.setAdapter(adapter);
    }

    private void showStudentSelectionDialog(SeatingArrangement seat) {
        List<Student> enrolledStudents = db.enrollmentDao().getStudentsInClass(classID);
        List<Student> availableStudents = new ArrayList<>();

        // Filter students who do NOT have a seat
        for (Student student : enrolledStudents) {
            if (db.seatDao().getSeatsForStudent(student.getStudentID()).isEmpty()) {
                availableStudents.add(student);
            }
        }

        // If no available students, show message and return
        if (availableStudents.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("No Available Students")
                    .setMessage("All enrolled students already have a seat.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        // Prepare names for selection dialog
        String[] studentNames = new String[availableStudents.size()];
        for (int i = 0; i < availableStudents.size(); i++) {
            studentNames[i] = availableStudents.get(i).getStudentName() + ": " + availableStudents.get(i).getStudentNumber();
        }

        // Show student selection dialog
        new AlertDialog.Builder(this)
                .setTitle("Assign a Student")
                .setItems(studentNames, (dialog, which) -> {
                    Student selectedStudent = availableStudents.get(which);
                    seat.setStudentID(selectedStudent.getStudentID());
                    db.seatDao().updateSeat(seat);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

}
