package com.example.schoolmanager.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanager.Database.ClassList;
import com.example.schoolmanager.R;

import java.util.List;

public class classContainerAdapter extends RecyclerView.Adapter<classContainerAdapter.ClassContainerViewHolder> {

    private List<ClassList> classLists;
    private OnCardClickListener onCardClickListener;

    public interface OnCardClickListener {
        void onCardClick(ClassList classList);
    }

    public classContainerAdapter(List<ClassList> classLists, OnCardClickListener onCardClickListener) {
        this.classLists = classLists;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ClassContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_class_container, parent, false);
        return new ClassContainerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassContainerViewHolder holder, int position) {
        ClassList classList = classLists.get(position);
        holder.teacherText.setText(classList.getTeacher());
        holder.classText.setText(classList.getSubject());
        holder.classTime.setText(classList.getTimeFrame());
        holder.classSchedule.setText(classList.getDate());
        holder.locationText.setText(classList.getRoom());

        // Handle click events safely
        holder.cardView.setOnClickListener(v -> {
            if (onCardClickListener != null) {
                onCardClickListener.onCardClick(classList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (classLists != null) ? classLists.size() : 0;
    }

    // ✅ Update list dynamically
    public void updateClassList(List<ClassList> newClassLists) {
        this.classLists = newClassLists;
        notifyDataSetChanged();
    }

    static class ClassContainerViewHolder extends RecyclerView.ViewHolder {
        private TextView classText, classTime, classSchedule,teacherText, locationText;
        private CardView cardView;

        public ClassContainerViewHolder(@NonNull View itemView) {
            super(itemView);
            classText = itemView.findViewById(R.id.class_container_class_text);
            teacherText = itemView.findViewById(R.id.class_container_teacher_text);
            locationText = itemView.findViewById(R.id.class_container_area_text);
            classTime = itemView.findViewById(R.id.class_container_room_text);
            classSchedule = itemView.findViewById(R.id.class_container_schedule_text);
            cardView = itemView.findViewById(R.id.class_container_body);
        }
    }
}
