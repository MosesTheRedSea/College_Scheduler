package com.example.college_app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventsForDate(LocalDate date) {
        ArrayList<Event> events = new ArrayList<>();
        for (Event event : eventsList) {
            if (event.getType() == 1) {
                if (event.getDate().equals(date)) {
                    events.add(event);
                }
            } else if (event.getType() == 2) {
                if (event.getDueDate().equals(date)) {
                    events.add(event);
                }
            } else if (event.getType() == 3) {
                if (event.getDate2().equals(date)) {
                    events.add(event);
                }
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
                            break;
                        }
                    }
                }
            }
        }
    }






    // Add an Exam to the List of Events
    private String Exam;

    private LocalDate date2;

    private String time2;
    private String Location;


    // Add an Assignment to the List of Events
    private String assingment;
    private LocalDate dueDate;

    private String Course;

    // Add an Course to the List of Events
    private String name;
    private String professor;
    private LocalDate date;
    private String time;
    private int type;

    public Event(String name, LocalDate date, String time, String professor, int t) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.professor = professor;
        type = t;
    }

    public Event(String assignment, LocalDate dueDate, String Course, int t) {
        this.assingment = assignment;
        this.dueDate = dueDate;
        this.Course = Course;
        this.type = t;

    }

    public Event(String exam, String time2, String location, LocalDate date2, int t) {
        this.Exam = exam;
        this.date2 = date2;
        this.time2 = time2;
        this.Location = location;
        this.type = t;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    public String getExam() {
        return Exam;
    }

    public void setExam(String exam) {
        Exam = exam;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAssingment() {
        return assingment;
    }

    public void setAssingment(String assingment) {
        this.assingment = assingment;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public int getType() {
        return this.type;
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

