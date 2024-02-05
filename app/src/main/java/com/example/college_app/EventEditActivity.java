package com.example.college_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventCourseET;
    private TextView eventDateET, eventTimeET, eventProfessorET;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        intitWidgets();
        time = LocalTime.now();
        eventDateET.setText("Date:  "+ CalendarUtils.formattedDate(CalendarUtils.selectDate));
        eventTimeET.setText("Time:  "+ CalendarUtils.formattedTime(time));
    }

    private void intitWidgets() {
        eventDateET = findViewById(R.id.eventDateET);
        eventCourseET = findViewById(R.id.eventCourseET);
        eventTimeET = findViewById(R.id.eventTimeET);
        eventProfessorET = findViewById(R.id.eventProfessorET);
    }

    public void saveEventAction(View view) {
        String eventName = eventCourseET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectDate, time);
        Event.eventsList.add(newEvent);
        finish();
    }
}