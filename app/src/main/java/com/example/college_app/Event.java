package com.example.college_app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventsForDate(LocalDate date) {
        ArrayList<Event> events = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().equals(date)) {
                events.add(event);
            }
        }
        return events;
    }

    public static void removeItem(String Course, String Professor, String Time, LocalDate date) {
        for (int i = 0; i < eventsList.size(); ++i) {
            if (eventsList.get(i).getDate().equals(date)) {
                if (eventsList.get(i).getName().equals(Course)) {
                    if (eventsList.get(i).getProfessor().equals(Professor)) {
                        if (eventsList.get(i).getTime().equals(Time)) {
                            eventsList.remove(i);
                        }
                    }
                }
            }
        }
    }
    private String name;

    private String professor;
    private LocalDate date;
    private String time;

    public Event(String name, LocalDate date, String time, String professor) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfessor() {
        return this.professor;
    }

    public void setProfessor(String prof) {
        this.professor = prof;
    }
}

