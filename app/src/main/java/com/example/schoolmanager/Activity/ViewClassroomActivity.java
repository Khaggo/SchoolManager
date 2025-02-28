package com.example.schoolmanager.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanager.Adapter.SeatAdapter;
import com.example.schoolmanager.Database.SeatingArrangement;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;

import java.util.List;

public class ViewClassroomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_classroom);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int classID = getIntent().getIntExtra("class_id", -1);

        RecyclerView recyclerView = findViewById(R.id.view_classroom_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        List<SeatingArrangement> seats = MyApplication.getDatabase().seatDao().getSeatsForClass(classID);

        if (seats.isEmpty()) {
            for (int i = 1; i <= 30; i++) {
                SeatingArrangement newSeat = new SeatingArrangement(classID, i, null); // No student assigned yet
                MyApplication.getDatabase().seatDao().insertSeat(newSeat);
            }
            seats =MyApplication.getDatabase().seatDao().getSeatsForClass(classID);
        }

        SeatAdapter adapter = new SeatAdapter(seats, this, MyApplication.getDatabase().seatDao(), MyApplication.getDatabase().seatDao());
        recyclerView.setAdapter(adapter);


    }
}