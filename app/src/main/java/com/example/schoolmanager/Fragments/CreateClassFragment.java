package com.example.schoolmanager.Fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.schoolmanager.Database.ClassList;
import com.example.schoolmanager.Database.Teacher;
import com.example.schoolmanager.MainActivity;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CreateClassFragment extends Fragment {

    private TextView dateTextView, timeTextView;

    private Button chooseDateButton, chooseTimeButton, saveButton;

    private AutoCompleteTextView subjectTextField, roomTextField;

    private String selectedDayOfWeek = "";
    private int startHour, startMinute, endHour, endMinute;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewer =  inflater.inflate(R.layout.fragment_create_class, container, false);

        dateTextView = viewer.findViewById(R.id.text_view_date);
        timeTextView = viewer.findViewById(R.id.text_view_time);

        chooseDateButton = viewer.findViewById(R.id.create_class_date_chooser_button);
        chooseTimeButton = viewer.findViewById(R.id.create_class_time_chooser_button);
        saveButton = viewer.findViewById(R.id.create_class_save_button);

        subjectTextField = viewer.findViewById(R.id.create_class_subject_text_field);
        roomTextField = viewer.findViewById(R.id.create_class_room_text_field);

        chooseDateButton.setOnClickListener(v -> showDayOfWeekPicker());
        chooseTimeButton.setOnClickListener(v -> showTimePicker());


        setupSubjectAutoComplete();
        setupRoomAutoComplete();

        subjectTextField.setOnClickListener(v -> subjectTextField.showDropDown());
        roomTextField.setOnClickListener(v -> roomTextField.showDropDown());
        saveButton.setOnClickListener(v -> saveClassDetails());




        return viewer;

    }

    private void saveClassDetails() {
        String subject = subjectTextField.getText().toString().trim();
        String room = roomTextField.getText().toString().trim();
        String dayOfWeek = selectedDayOfWeek;
        String time = timeTextView.getText().toString().trim();

        if (subject.isEmpty() || room.isEmpty() || dayOfWeek.isEmpty() || time.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {



            ClassList classList = new ClassList(
                    subject,
                    MainActivity.getTeacher().getTeacherName(),
                    room,
                    dayOfWeek,
                    time
            );
            MyApplication.getDatabase().classListDao().insertClassList(classList);

            // Navigate to CLRMFrag
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_frame_container, new ClassListFragment())
                    .addToBackStack(null)
                    .commit();

            // Clear the fields
            subjectTextField.setText("");
            roomTextField.setText("");
            dateTextView.setText("Day of Week: ");
            timeTextView.setText("Time: ");


            Toast.makeText(getActivity(), "Class saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDayOfWeekPicker() {
        final String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Select Day of Week")
                .setItems(daysOfWeek, (dialog, which) -> {
                    selectedDayOfWeek = daysOfWeek[which];
                    dateTextView.setText("Day of Week: " + selectedDayOfWeek);
                })
                .create()
                .show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                (view, hourOfDay, minute) -> {
                    startHour = hourOfDay;
                    startMinute = minute;

                    showEndTimePicker();
                }, currentHour, currentMinute, false);
        timePickerDialog.show();
    }

    private void showEndTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endHour = hourOfDay;
                        endMinute = minute;

                        String startTime = formatTime(startHour, startMinute);
                        String endTime = formatTime(endHour, endMinute);
                        timeTextView.setText("Time: " + startTime + " - " + endTime);
                    }
                }, currentHour, currentMinute, false);
        timePickerDialog.show();
    }
    private String formatTime(int hour, int minute) {
        String period;
        if (hour < 12) {
            period = "AM";
        } else {
            period = "PM";
        }

        int formattedHour = hour % 12;
        if (formattedHour == 0) {
            formattedHour = 12;
        }

        return String.format("%02d:%02d %s", formattedHour, minute, period);
    }


    private void setupSubjectAutoComplete() {
        List<String> subjects = new ArrayList<>();
        subjects.add("Introduction to Computing");
        subjects.add("Programming 1");
        subjects.add("Data Structures and Algorithms");
        subjects.add("Web Development");
        subjects.add("Database Management Systems");
        subjects.add("Networking Fundamentals");
        subjects.add("Software Engineering");
        subjects.add("Mobile Application Development");
        subjects.add("Information Security");
        subjects.add("Capstone Project");

        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                subjects
        );

        subjectTextField.setAdapter(subjectAdapter);
    }

    private void setupRoomAutoComplete() {
        List<String> rooms = new ArrayList<>();
        rooms.add("1010");
        rooms.add("1009");
        rooms.add("1008");
        rooms.add("1007");
        rooms.add("1110");
        rooms.add("1109");
        rooms.add("1108");
        rooms.add("1107");
        rooms.add("1210");
        rooms.add("1209");
        rooms.add("1208");
        rooms.add("1207");

        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                rooms
        );
        roomTextField.setAdapter(roomAdapter);
    }

}