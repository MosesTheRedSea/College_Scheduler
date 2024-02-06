package com.example.college_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;


public class EventEditActivity extends AppCompatActivity {
//This is for the add page in week view
    private EditText eventCourseET;
    private TextView eventDateET, eventTimeET, eventProfessorET;

    private EditText eventExamET;
    private TextView eventDateExET, eventTimeExET, eventLocationET;

    private EditText eventAssignET;
    private TextView assignDateET, eventAssignCourseET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String buttonClicked = getIntent().getStringExtra("button_clicked");
        if ("Course button".equals(buttonClicked)) {
            setContentView(R.layout.activity_event_edit);
            intitWidgets();
            eventDateET.setText("Date:  " + CalendarUtils.formattedDate(CalendarUtils.selectDate));
        } else  if ("Exam button".equals(buttonClicked )) {
            setContentView(R.layout.exam_event_actrivity);
            intitWidgets2();
            eventDateExET.setText("Date:  " + CalendarUtils.formattedDate(CalendarUtils.selectDate));
        } else if ("Assign button".equals(buttonClicked)) {
            setContentView(R.layout.assignment_activity);
            initWidgets3();
            assignDateET.setText("Date:  " + CalendarUtils.formattedDate(CalendarUtils.selectDate));
        }

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
        Event newEvent = new Event(eventName, CalendarUtils.selectDate, eventTime, professorN, 1);
        Event.eventsList.add(newEvent);
        finish();
    }

    public void saveAssignAction(View view) {
        String assignmentName = eventAssignET.getText().toString();
        String assignmentCourse = eventAssignCourseET.getText().toString();
        Event newEvent = new Event(assignmentName, CalendarUtils.selectDate, assignmentCourse, 2);
        Event.eventsList.add(newEvent);
        finish();
    }

    public void saveExamAction(View view) {
        String examName = eventExamET.getText().toString();
        String examTime = eventTimeExET.getText().toString();
        String eventLocation = eventLocationET .getText().toString();
        Event newEvent = new Event(examName, examTime, eventLocation, CalendarUtils.selectDate, 3);
        Event.eventsList.add(newEvent);
        finish();
    }






}