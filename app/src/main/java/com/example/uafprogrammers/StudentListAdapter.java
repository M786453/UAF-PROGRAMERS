package com.example.uafprogrammers;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter {

    private Context context;

    private LayoutInflater layoutInflater;

    private ArrayList<Student> studentsList;

    public static int prev_student_points = -1;

    public static int prev_student_rank = -1;


    StudentListAdapter(Context context, ArrayList<Student> studentsList){

        this.context = context;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.studentsList = studentsList;



    }

    @Override
    public int getCount() {
        return studentsList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View rank_row, ViewGroup viewGroup) {

        rank_row = layoutInflater.inflate(R.layout.leaderboard_student_row, null);

        try {



            TextView txtStudentName = rank_row.findViewById(R.id.txt_student);
            TextView txtStudentRank = rank_row.findViewById(R.id.txt_rank);
            TextView txtStudentPoints = rank_row.findViewById(R.id.txt_points);
            TextView txtStudentCompetitions = rank_row.findViewById(R.id.txt_competitions);


            txtStudentName.setText(studentsList.get(i).getName());

            if(prev_student_points == -1 && prev_student_rank == -1){
                prev_student_points = studentsList.get(i).getPoints();
                prev_student_rank = 1;
            }else if(prev_student_points == studentsList.get(i).getPoints()){
                studentsList.get(i).setRank(prev_student_rank);
            }else{
                prev_student_points = studentsList.get(i).getPoints();

                studentsList.get(i).setRank(++prev_student_rank);
            }

            txtStudentRank.setText(studentsList.get(i).getRank() + "");
            txtStudentCompetitions.setText(studentsList.get(i).getCompetitions_participated() + "");
            txtStudentPoints.setText(studentsList.get(i).getPoints() + "");


            txtStudentName.setTypeface(null, Typeface.NORMAL);
            txtStudentRank.setTypeface(null, Typeface.NORMAL);
            txtStudentCompetitions.setTypeface(null, Typeface.NORMAL);
            txtStudentPoints.setTypeface(null, Typeface.NORMAL);




        }catch (Exception e){

            e.printStackTrace();

        }

        return rank_row;
    }
}
