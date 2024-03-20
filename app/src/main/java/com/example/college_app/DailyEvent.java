package com.example.college_app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class DailyEvent implements Parcelable {

    public static ArrayList<DailyEvent> eventsList = new ArrayList<>();

    public static ArrayList<DailyEvent> assignmentList = new ArrayList<>();

    // Important Course Information
    private String courseName;
    private LocalDate courseDate;
    private String courseTime;
    private String courseProfessor;

    // Important Exam Information
    private String exam;
    private LocalDate examDate;
    private String examTime;
    private String examLocation;

    // Important Assignment Information
    private String assignmentName;
    private LocalDate assignmentDate;
    private String assignmentCourseName;

    // String Event Type - Task Identifer
    private String DailyEventType;
    private String id;


    public static DailyEvent findEvent(DailyEvent eventToFind) {
        for (int i = 0; i < eventsList.size(); ++i) {
            if (equalCourse(eventToFind, eventsList.get(i))) {
                return eventsList.get(i);
            }
        }
        return null;
    }
    public static void makeCourseEdits(DailyEvent event, String CourseName, LocalDate courseDate, String courseTime, String courseProfessor) {
        DailyEvent findEvent = findEvent(event);
        findEvent.setCourseName(CourseName);
        findEvent.setCourseDate(courseDate);
        findEvent.setCourseTime(courseTime);
        findEvent.setCourseProfessor(courseProfessor);
    }


    // This Will Return only the assignments for this date
    public static ArrayList<DailyEvent> assignmentsForDate(LocalDate date) {
        ArrayList<DailyEvent> events = new ArrayList<>();


        
        return events;
    }

    public static ArrayList<DailyEvent> eventsForDate(LocalDate date) {
        ArrayList<DailyEvent> events = new ArrayList<>();
        for (DailyEvent event : eventsList) {
            switch (event.getDailyEventType()) {
                case "Course":
                    if (event.getCourseDate().equals(date)) {
                        events.add(event);
                    }
                    break;
                case "Assignment":
                    if (event.getAssignmentDate().equals(date)) {
                        events.add(event);
                    }
                    break;
                case "Exam":
                    if (event.getExamDate().equals(date)) {
                        events.add(event);
                    }
                    break;
            }
        }
        return events;
    }

    public static void sortAssignments(String what, ArrayList<DailyEvent> arr) {

    }

    public static void removeEvent(DailyEvent e) {
        switch (e.getDailyEventType()) {
            case "Course":
                for (int i = 0; i < eventsList.size(); ++i) {
                    if (equalCourse(e, eventsList.get(i))) {
                        eventsList.remove(i);
                        break;
                    }
                }
                break;
            case "Assignment":
                for (int i = 0; i < eventsList.size(); ++i) {
                    if (equalAssignment(e, eventsList.get(i))) {
                        eventsList.remove(i);
                        break;
                    }
                }
                break;
            case "Exam":
                for (int i = 0; i < eventsList.size(); ++i) {
                    if (equalExam(e, eventsList.get(i))) {
                        eventsList.remove(i);
                        break;
                    }
                }
                break;
        }
    }

    public static boolean equalCourse(DailyEvent one, DailyEvent two) {
        if (one.courseName.equals(two.courseName)) {
            if (one.courseDate.equals(two.courseDate)) {
                if (one.courseTime.equals(two.courseTime)) {
                    if(one.courseProfessor.equals(two.courseProfessor)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean equalExam(DailyEvent one, DailyEvent two) {
        if (one.exam.equals(two.exam)) {
            if (one.examDate.equals(two.examDate)) {
                if (one.examTime.equals(two.examTime)) {
                    if (one.examLocation.equals(two.examLocation)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean equalAssignment(DailyEvent one, DailyEvent two) {
        if (one.assignmentName.equals(two.assignmentName)) {
            if (one.assignmentDate.equals(two.assignmentDate)) {
                if (one.assignmentCourseName.equals(two.assignmentCourseName)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Assignment Constructor
    public DailyEvent(String a, LocalDate ad, String acn) {
        this.assignmentName = a;
        this.assignmentDate = ad;
        this.assignmentCourseName = acn;
        this.DailyEventType = "Assignment";
        this.id = UUID.randomUUID().toString();
    }


    //Exam Constructor
    public DailyEvent(String en,  String et, LocalDate ed, String el) {
        this.exam = en;
        this.examDate = ed;
        this.examTime = et;
        this.examLocation = el;
        this.DailyEventType = "Exam";
        this.id = UUID.randomUUID().toString();
    }

    // Course Constructor
    public DailyEvent(String cn, LocalDate cd, String ct, String cp) {
        this.courseName = cn;
        this.courseDate = cd;
        this.courseTime = ct;
        this.courseProfessor = cp;
        this.DailyEventType = "Course";
        this.id = UUID.randomUUID().toString();

    }

    protected DailyEvent(Parcel in) {
        DailyEventType = in.readString();
        id = in.readString();

        // Checking that type the object may be
        assert DailyEventType != null;
        if (DailyEventType.equals("Course")) {

            courseName = in.readString();

            long epochDay = in.readLong();
            courseDate = epochDay != -1 ? LocalDate.ofEpochDay(epochDay) : null;

            courseTime = in.readString();

            courseProfessor = in.readString();

        } else if (DailyEventType.equals("Assignment")) {

            assignmentName = in.readString();

            // Get the Local Date
            long epochDay = in.readLong();
            assignmentDate = epochDay != -1 ? LocalDate.ofEpochDay(epochDay) : null;

            assignmentCourseName = in.readString();

        }  else if (DailyEventType.equals("Exam")) {

            exam = in.readString();

            // Get the Local Date
            long epochDay = in.readLong();
            examDate = epochDay != -1 ? LocalDate.ofEpochDay(epochDay) : null;

            examTime = in.readString();

            examLocation = in.readString();
        }
    }

    public static final Creator<DailyEvent> CREATOR = new Creator<DailyEvent>() {
        @Override
        public DailyEvent createFromParcel(Parcel in) {
            return new DailyEvent(in);
        }

        @Override
        public DailyEvent[] newArray(int size) {
            return new DailyEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(DailyEventType); // Type of Event Assignment - Exam - Course
        dest.writeString(id);

        // Checking that type the object may be
        if (DailyEventType.equals("Course")) {

            dest.writeString(courseName);

            dest.writeLong(courseDate != null ? courseDate.toEpochDay() : -1);
           // dest.writeSerializable(courseDate != null ? courseDate.toEpochDay() : null);

            dest.writeString(courseTime);

            dest.writeString(courseProfessor);

        } else if (DailyEventType.equals("Assignment")) {

            dest.writeString(assignmentName);

            dest.writeLong(assignmentDate != null ? assignmentDate.toEpochDay() : -1);
            // dest.writeSerializable(assignmentDate != null ? assignmentDate.toEpochDay() : null);

            dest.writeString(assignmentCourseName);

        } else if (DailyEventType.equals("Exam")) {

            dest.writeString(exam);

            dest.writeLong(examDate != null ? examDate.toEpochDay() : -1);

            dest.writeString(examTime);

            dest.writeString(examLocation);

        }
    }

    // Accessors and Modifier Methods for Implementation
    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(LocalDate courseDate) {
        this.courseDate = courseDate;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        this.courseProfessor = courseProfessor;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamLocation() {
        return examLocation;
    }

    public void setExamLocation(String examLocation) {
        this.examLocation = examLocation;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public String getAssignmentCourseName() {
        return assignmentCourseName;
    }

    public void setAssignmentCourseName(String assignmentCourseName) {
        this.assignmentCourseName = assignmentCourseName;
    }

    public String getDailyEventType() {
        return DailyEventType;
    }

    public void setDailyEventType(String dailyEventType) {
        DailyEventType = dailyEventType;
    }
}
