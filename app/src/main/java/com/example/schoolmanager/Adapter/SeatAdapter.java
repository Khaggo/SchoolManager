package com.example.schoolmanager.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanager.Database.SeatDao;
import com.example.schoolmanager.Database.SeatingArrangement;
import com.example.schoolmanager.Database.Student;
import com.example.schoolmanager.R;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {

    private List<SeatingArrangement> seatList;
    private Context context;
    private SeatDao seatDao;
    private SeatDao studentDao;

    public SeatAdapter(List<SeatingArrangement> seatList, Context context, SeatDao seatDao, SeatDao studentDao) {
        this.seatList = seatList;
        this.context = context;
        this.seatDao = seatDao;
        this.studentDao = studentDao;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        SeatingArrangement seat = seatList.get(position);
        holder.seatButton.setText("Seat " + seat.getSeatNumber());

        if (seat.getStudentID() != null) {
            // Fetch student details
            Student student = studentDao.getStudentById(seat.getStudentID());
            holder.seatButton.setText(student.getStudentName());
            holder.seatButton.setBackgroundColor(Color.BLUE);
        } else {
            holder.seatButton.setBackgroundColor(Color.GREEN);
        }

        holder.seatButton.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    public static class SeatViewHolder extends RecyclerView.ViewHolder {
        Button seatButton;

        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            seatButton = itemView.findViewById(R.id.buttonSeat);
        }
    }
}
