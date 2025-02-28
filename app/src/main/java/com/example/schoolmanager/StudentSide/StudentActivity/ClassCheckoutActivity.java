package com.example.schoolmanager.StudentSide.StudentActivity;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanager.Adapter.classContainerAdapter;
import com.example.schoolmanager.Database.ClassList;
import com.example.schoolmanager.Database.Enrollment;
import com.example.schoolmanager.MyApplication;
import com.example.schoolmanager.R;
import com.example.schoolmanager.StudentSide.StudentMainActivity;

import java.util.ArrayList;
import java.util.List;

public class ClassCheckoutActivity extends AppCompatActivity {

    List<ClassList> bucketClassList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_class_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button checkoutButton = findViewById(R.id.activity_class_checkout_confirm_button);
        Button cancelButton = findViewById(R.id.activity_class_checkout_cancel_button);


        bucketClassList = (ArrayList<ClassList>) getIntent().getSerializableExtra("bucketList");


        RecyclerView recyclerView = findViewById(R.id.activity_class_checkout_recycler_view);
        classContainerAdapter adapter = new classContainerAdapter(
                bucketClassList,
                (ClassList classList) -> new AlertDialog.Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("Do you want to remove this to your bucket list")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            bucketClassList.remove(classList);
                            this.recreate();

                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show()
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        cancelButton.setOnClickListener(view -> {
            finish();
        });

        checkoutButton.setOnClickListener(view -> {

            if (bucketClassList.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("No class registered")
                        .show();

                return;
            }
            new AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Do you want to confirm the classes registered?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        StudentMainActivity.student.setRegisteredClasses(bucketClassList);
                        MyApplication.getDatabase().studentTeacherDao().updateStudent(StudentMainActivity.student);

                        for(ClassList cl : bucketClassList) {
                            MyApplication.getDatabase().enrollmentDao().enrollStudent(new Enrollment(StudentMainActivity.student.getStudentID(), cl.getClassID()));
                        }

                        finish();


                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });

    }

}