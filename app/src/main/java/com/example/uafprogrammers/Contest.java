package com.example.uafprogrammers;

public class Contest {

    private String title;
    private String time;
    private String level;
    private String noOfProblems;
    private String totalPoints;
    private String link;

    public Contest(String title, String time, String level, String noOfProblems, String totalPoints, String link){

        this.title = title;
        this.time = time;
        this.level = level;
        this.noOfProblems = noOfProblems;
        this.totalPoints = totalPoints;
        this.link = link;

    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getLevel() {
        return level;
    }

    public String getNoOfProblems() {
        return noOfProblems;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public String getLink() {
        return link;
    }
}
