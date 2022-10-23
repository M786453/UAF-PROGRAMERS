package com.example.uafprogrammers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();


        findViewById(R.id.btnLeaderboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, LeaderboardActivity.class));
            }
        });

        findViewById(R.id.btnUpcommingContests).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, UpcomingContestActivity.class));
            }
        });

        findViewById(R.id.btnPastContests).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, PastContestsActivity.class));
            }
        });

        findViewById(R.id.btnContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    String[] strTo = {"ahteshamsarwar333@gmail.com"};
                    intent.putExtra(Intent.EXTRA_EMAIL, strTo);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "App Feedback");
                    startActivity(intent);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }



}