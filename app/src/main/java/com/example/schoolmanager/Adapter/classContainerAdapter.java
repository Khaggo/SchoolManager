package com.example.schoolmanager.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanager.R;

public class classContainerAdapter extends RecyclerView.Adapter<classContainerAdapter.ClassContainerViewHolder> {


    @NonNull
    @Override
    public ClassContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassContainerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ClassContainerViewHolder extends RecyclerView.ViewHolder {

        private TextView classText, classTime, classSchedule;
        public ClassContainerViewHolder(@NonNull View itemView) {
            super(itemView);
            classText = itemView.findViewById(R.id.class_container_class_text);
            classTime = itemView.findViewById(R.id.class_container_room_text);
            classSchedule = itemView.findViewById(R.id.class_container_schedule_text);
        }
    }
}
