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
import com.example.schoolmanager.R;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {

    private List<SeatingArrangement> seatList;
    private Context context;
    private SeatDao seatDao;
    private OnSeatClickListener onSeatClickListener;

    public interface OnSeatClickListener {
        void onSeatClick(SeatingArrangement seat);
    }

    public SeatAdapter(List<SeatingArrangement> seatList, Context context, SeatDao seatDao, OnSeatClickListener onSeatClickListener) {
        this.seatList = seatList;
        this.context = context;
        this.seatDao = seatDao;
        this.onSeatClickListener = onSeatClickListener;
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

        if (seat.getStudentID() != null) {
            holder.seatButton.setBackgroundColor(Color.RED);
            holder.seatButton.setText("Occupied");
        } else {
            holder.seatButton.setBackgroundColor(Color.GREEN);
            holder.seatButton.setText("Available");
        }

        holder.seatButton.setOnClickListener(v -> onSeatClickListener.onSeatClick(seat));

        holder.seatButton.setOnLongClickListener(v -> {
            if (seat.getStudentID() != null) {
                new android.app.AlertDialog.Builder(context)
                        .setTitle("Remove Student")
                        .setMessage("Do you want to remove the student from this seat?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            seat.setStudentID(null);
                            seatDao.updateSeat(seat);
                            notifyDataSetChanged();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
            return true;
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
