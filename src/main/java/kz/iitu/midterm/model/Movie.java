package kz.iitu.midterm.model;

import org.springframework.stereotype.Component;

@Component
public class Movie {
    private int id;
    private String title;
    private String morningTime;
    private String afternoonTime;
    private String eveningTime;

    public Movie() {
    }

    public Movie(int id, String title, String morningTime, String afternoonTime, String eveningTime) {
        this.id = id;
        this.title=title;
        this.morningTime = morningTime;
        this.afternoonTime = afternoonTime;
        this.eveningTime = eveningTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMorningTime() {
        return morningTime;
    }

    public void setMorningTime(String morningTime) {
        this.morningTime = morningTime;
    }

    public String getAfternoonTime() {
        return afternoonTime;
    }

    public void setAfternoonTime(String afternoonTime) {
        this.afternoonTime = afternoonTime;
    }

    public String getEveningTime() {
        return eveningTime;
    }

    public void setEveningTime(String eveningTime) {
        this.eveningTime = eveningTime;
    }

    @Override
    public String toString() {
        return title;
    }

    public String seeTime(){return "Schedule:\n" + morningTime + "\n" + afternoonTime + "\n" + eveningTime;}
}
