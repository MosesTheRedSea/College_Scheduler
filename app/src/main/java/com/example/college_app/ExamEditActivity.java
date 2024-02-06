package com.example.college_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class ExamEditActivity extends AppCompatActivity {
    private EditText eventExamET;

    private TextView eventDateExET, eventTimeExET, eventLocationET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_event_actrivity);
        intitWidgets();
        eventDateExET.setText("Date:  " + CalendarUtils.formattedDate(CalendarUtils.selectDate));
    }

    private void intitWidgets() {
        eventDateExET = findViewById(R.id.eventDateExET);
        eventExamET = findViewById(R.id.eventExamET);
        eventTimeExET = findViewById(R.id.eventTimeExET);
        eventLocationET = findViewById(R.id.eventLocationET);
    }

    /*
    public void saveExamAction(View view) {
        String examName = eventExamET.getText().toString();
        String examTime = eventTimeExET.getText().toString();
        String eventLocation = eventLocationET .getText().toString();
        Event newEvent = new Event(examName, examTime, eventLocation, CalendarUtils.selectDate, 3);
        Event.eventsList.add(newEvent);
        finish();
    }
     */
}
