package com.coursework;

import java.util.ArrayList;

public class Coach {
    private String coachName;
    private ArrayList<SwimmingLesson> lessons = new ArrayList<>();
    public Coach(String coachName){
        this.coachName = coachName;
    }

    public String getCoachName() {
        return coachName;
    }
    public String toString(){
        return "Coach name:\t"+getCoachName();
    }
}
