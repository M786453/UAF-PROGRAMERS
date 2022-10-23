package com.example.uafprogrammers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class PastContestsActivity extends AppCompatActivity {

    private ListView contestListview;

    private TextView txtStatus;

    private ArrayList<Contest> contestArrayList;

    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference databaseReference;

    private ContestListAdapter contestListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_contests);

        getSupportActionBar().hide();

        txtStatus = findViewById(R.id.txtStatusPastContestActivity);

        contestListview = findViewById(R.id.listview_past_contests);

        contestArrayList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("PastContests");

        contestListAdapter = new ContestListAdapter(PastContestsActivity.this, contestArrayList);

        contestListview.setAdapter(contestListAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                contestArrayList.clear();

                try{

                    for(DataSnapshot contest: snapshot.getChildren()){

                        contestArrayList.add(new Contest(contest.child("title").getValue() + "", contest.child("time").getValue() + "",
                                contest.child("level").getValue() + "", contest.child("problems").getValue() + "",
                                contest.child("TotalPoints").getValue() + "", contest.child("link").getValue() + ""));



                    }

                    contestListAdapter.notifyDataSetChanged();

                    if (contestArrayList.size() > 0){

                        txtStatus.setVisibility(View.GONE);
                        contestListview.setVisibility(View.VISIBLE);

                    }else{

                        txtStatus.setText("No Past Contests Here...");
                        txtStatus.setVisibility(View.VISIBLE);
                        contestListview.setVisibility(View.GONE);


                    }

                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Contest-Fetch", error.getMessage());
            }
        });

    }
}