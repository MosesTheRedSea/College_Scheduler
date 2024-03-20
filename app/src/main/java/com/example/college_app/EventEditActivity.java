package com.example.college_app;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;

public class EventEditActivity extends AppCompatActivity  {
//This is for the add page in week view
    private EditText eventCourseET;
    private TextView eventDateET, eventTimeET, eventProfessorET;
    private EditText eventExamET;
    private TextView eventDateExET, eventTimeExET, eventLocationET;
    private EditText eventAssignET;
    private TextView assignDateET, eventAssignCourseET;

    //-----------------------------------------------------------------\\
    private EditText editCourseName, editCourseDate, editCourseTime, editCourseProfessor;

    // Edit Exam Information
    private EditText editExamName, editExamDate, editExamTime, editExamLocation;

    // Edit Assignment Information
    private EditText editAssignmentCourse, editAssignmentName, editAssignDueDate;
    private DailyEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        event = getIntent().getParcelableExtra("event", DailyEvent.class);
        String buttonClicked = getIntent().getStringExtra("button_clicked");

        if (event == null) {
            if ("Course button".equals(buttonClicked)) {
                setContentView(R.layout.activity_event_edit);
                intitWidgets();
                eventDateET.setText(CalendarUtils.selectDate.toString());
            } else  if ("Exam button".equals(buttonClicked )) {
                setContentView(R.layout.exam_event_actrivity);
                intitWidgets2();
                eventDateExET.setText(CalendarUtils.selectDate.toString());
            } else if ("Assign button".equals(buttonClicked)) {
                setContentView(R.layout.assignment_activity);
                initWidgets3();
                assignDateET.setText(CalendarUtils.selectDate.toString());
            }
        } else {
            // We're editing an existing Event
            switch (event.getDailyEventType()) {
                case "Course":
                    // Set the Course Edit xml Layout
                    setContentView(R.layout.activity_event_change);
                    // Initialize the Edit Text Boxes
                    initCourseChanges();
                    // Have the boxes hold the default event Values
                    editCourseName.setText(event.getCourseName());
                    editCourseDate.setText(event.getCourseDate().toString());
                    editCourseTime.setText(event.getCourseTime());
                    editCourseProfessor.setText(event.getCourseProfessor());
                    break;
                case "Assignment":
                    // Set the Assignment Edit xml Layout
                    setContentView(R.layout.activity_assign_change);
                    initAssignmentChanges();

                    editAssignmentName.setText(event.getAssignmentName());
                    editAssignDueDate.setText(event.getAssignmentDate().toString());
                    editAssignmentCourse.setText(event.getAssignmentCourseName());
                    break;
                case "Exam":
                    // Set the Exam Edit xml Layout
                    setContentView(R.layout.activity_exam_change);
                    initExamChanges();

                    editExamName.setText(event.getExam());
                    editExamDate.setText(event.getExamDate().toString());
                    editExamTime.setText(event.getExamTime());
                    editExamLocation.setText(event.getExamLocation());
                    break;
            }
        }
    }

    public void saveCourseData(View view) {
        switch (event.getDailyEventType()) {
            case "Course":
                event.setCourseName(editCourseName.getText().toString());
                event.setCourseDate(LocalDate.parse(editCourseDate.getText().toString()));
                event.setCourseProfessor(editCourseProfessor.getText().toString());
                event.setCourseTime(editCourseTime.getText().toString());
                break;

            case "Assignment":
                event.setAssignmentName(editAssignmentName.getText().toString());
                event.setAssignmentDate(LocalDate.parse(editAssignDueDate.getText().toString()));
                event.setAssignmentCourseName(editAssignmentCourse.getText().toString());
                break;

            case "Exam":
                event.setExam(editExamName.getText().toString());
                event.setExamDate(LocalDate.parse(editExamDate.getText().toString()));
                event.setExamTime(editExamTime.getText().toString());
                event.setExamLocation(editExamLocation.getText().toString());
                break;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("updated_event", event);
        setResult(RESULT_OK, resultIntent);

        finish();
    }

    // Unneeded Method - Don't have to implement
    /*
    public LocalDate convertStringToDate(String date) {
        // Split the Date output by dashes
        String[] arr = date.split("-");

        return null;
    }
    */


    public void initCourseChanges() {
        editCourseName = findViewById(R.id.editCourseName);
        editCourseDate = findViewById(R.id.editCourseDate);
        editCourseTime = findViewById(R.id.editCourseTime);
        editCourseProfessor = findViewById(R.id.editCourseProfessor);

    }

    public void initAssignmentChanges() {

        editAssignmentCourse = findViewById(R.id.editAssignmentCourse);
        editAssignmentName = findViewById(R.id.EditAssignment);
        editAssignDueDate = findViewById(R.id.assignDateChange);

    }

    public void initExamChanges() {
        editExamName = findViewById(R.id.changeExam);
        editExamDate = findViewById(R.id.changeExamDate);
        editExamTime = findViewById(R.id.changeExamTime);
        editExamLocation = findViewById(R.id.changeExamLoc);
    }

    private void intitWidgets() {
        eventDateET = findViewById(R.id.eventDateET);
        eventCourseET = findViewById(R.id.eventCourseET);
        eventTimeET = findViewById(R.id.eventTimeET);
        eventProfessorET = findViewById(R.id.eventProfessorET);
    }

    private void intitWidgets2() {
        eventDateExET = findViewById(R.id.eventDateExET);
        eventExamET = findViewById(R.id.eventExamET);
        eventTimeExET = findViewById(R.id.eventTimeExET);
        eventLocationET = findViewById(R.id.eventLocationET);
    }

    private void initWidgets3() {
        eventAssignET = findViewById(R.id.eventAssignET);
        assignDateET = findViewById(R.id.assignDateET);
        eventAssignCourseET = findViewById(R.id.eventAssignCourseET);
    }

    public void saveEventAction(View view) {
        String eventName = eventCourseET.getText().toString();
        String eventTime = eventTimeET.getText().toString();
        String professorN = eventProfessorET.getText().toString();
        DailyEvent newEvent = new DailyEvent(eventName, CalendarUtils.selectDate, eventTime, professorN);
        DailyEvent.eventsList.add(newEvent);
        finish();
    }

    public void saveAssignAction(View view) {
        String assignmentName = eventAssignET.getText().toString();
        String assignmentCourse = eventAssignCourseET.getText().toString();
        DailyEvent newEvent = new DailyEvent(assignmentName, CalendarUtils.selectDate, assignmentCourse);
        DailyEvent.eventsList.add(newEvent);
        finish();
    }

    public void saveExamAction(View view) {
        String examName = eventExamET.getText().toString();
        String examTime = eventTimeExET.getText().toString();
        String eventLocation = eventLocationET .getText().toString();
        DailyEvent newEvent = new DailyEvent(examName, examTime, CalendarUtils.selectDate, eventLocation);
        DailyEvent.eventsList.add(newEvent);
        finish();
    }


}