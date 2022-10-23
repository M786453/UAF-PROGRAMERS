package com.example.uafprogrammers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ContestListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Contest> contestArrayList;

    public ContestListAdapter(Context context, ArrayList<Contest> contestArrayList){

        this.context = context;
        this.contestArrayList = contestArrayList;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return contestArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return contestArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contest_row_view, ViewGroup viewGroup) {


        contest_row_view = layoutInflater.inflate(R.layout.contest_row, null);

        try {

            TextView txtTitle = contest_row_view.findViewById(R.id.txtContestTitle);
            TextView txtTime = contest_row_view.findViewById(R.id.txtContestTime);
            TextView txtLevel = contest_row_view.findViewById(R.id.txtContestDifficulty);
            TextView txtNoOfProblems = contest_row_view.findViewById(R.id.txtNoOfProblems);
            TextView txtTotalPoints = contest_row_view.findViewById(R.id.txtTotalPoints);
            CardView contestCardView = contest_row_view.findViewById(R.id.contestCardView);

            txtTitle.setText(contestArrayList.get(i).getTitle());
            txtTime.setText(contestArrayList.get(i).getTime());
            txtLevel.setText(contestArrayList.get(i).getLevel());
            txtNoOfProblems.setText(contestArrayList.get(i).getNoOfProblems());
            txtTotalPoints.setText(contestArrayList.get(i).getTotalPoints());

            contestCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri link_uri = Uri.parse(contestArrayList.get(i).getLink());
                    Intent link_intent = new Intent(Intent.ACTION_VIEW, link_uri);
                    context.startActivity(link_intent);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

        return contest_row_view;
    }
}
