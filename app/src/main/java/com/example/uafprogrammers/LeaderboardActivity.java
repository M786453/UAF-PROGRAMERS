package com.example.uafprogrammers;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardActivity extends AppCompatActivity {

    private ArrayList<Student> students_list;

    private ListView students_listview;
    private TextView txtStatus;

    private StudentListAdapter studentListAdapter;

    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        getSupportActionBar().hide();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Students");

        students_list = new ArrayList<>();

        // Initializing UI components

        students_listview = findViewById(R.id.students_listview);
        txtStatus = findViewById(R.id.txtStatus);
        studentListAdapter = new StudentListAdapter(LeaderboardActivity.this, students_list);

        students_listview.setAdapter(studentListAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                students_list.clear();

                StudentListAdapter.prev_student_points = -1;
                StudentListAdapter.prev_student_rank = -1;

                try {

                    for (DataSnapshot student : snapshot.getChildren()) {

                        students_list.add(new Student(student.child("name").getValue() + "", Integer.parseInt( "1"),
                                Integer.parseInt(student.child("contests").getValue() + ""), Integer.parseInt(student.child("points").getValue() + "")));


                    }

                    Collections.sort(students_list);

                    studentListAdapter.notifyDataSetChanged();

                    if (students_list.size() > 0){
                        txtStatus.setVisibility(View.GONE);
                        students_listview.setVisibility(View.VISIBLE);
                    }else{

                        txtStatus.setText("No Programmers Here...");
                        txtStatus.setVisibility(View.VISIBLE);
                        students_listview.setVisibility(View.GONE);
                    }


                }catch (Exception e){

                    e.printStackTrace();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("Data-Fetching", error.getMessage());

            }
        });



    }
}