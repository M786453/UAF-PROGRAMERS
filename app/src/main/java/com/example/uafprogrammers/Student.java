package com.example.uafprogrammers;

public class Student implements Comparable<Student>{

    private String name;
    private int rank;
    private int competitions_participated;
    private int points;

    public Student(String name, int rank, int competitions_participated, int points){

        this.name = name;
        this.rank = rank;
        this.competitions_participated = competitions_participated;
        this.points = points;


    }

    public String getName() {
        return name;
    }


    public int getRank() {
        return rank;
    }


    public int getCompetitions_participated() {
        return competitions_participated;
    }


    public int getPoints() {
        return points;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Student student) {
        return Integer.compare(student.points, this.points);
    }
}
