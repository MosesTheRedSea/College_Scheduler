package com.example.college_app;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;

public class EventChangeActivity extends AppCompatActivity {

    // Edit Course Information
    private EditText editCourseName, editCourseDate, editCourseTime, editCourseProfessor;

    // Edit Exam Information
    private TextView exanName, examDate, examTime, examLocation;
    private EditText editExamName, editExamTime, editExamLocation;

    // Edit Assignment Information
    private TextView assignmentName, DueDateName, CourseName;
    private EditText editAssignmentCourse, editAssignmentName;

    private EventAdapter eventAdapter;
    private ListView listView;
    private Spinner sortSpinner;

    private DailyEvent event;


    public void initCourseChanges() {

        editCourseName = findViewById(R.id.editCourseName);

        editCourseDate = findViewById(R.id.editCourseDate);

        editCourseTime = findViewById(R.id.editCourseTime);

        editCourseProfessor = findViewById(R.id.editCourseProfessor);

    }

    public void initAssignmentChanges() {



    }

    public void initExamChanges() {



    }

    public void saveCourseData(View view) {
        switch (event.getDailyEventType()) {
            case "Course":
                String courseName = editCourseName.getText().toString();
                String courseTime = editCourseTime.getText().toString();
                String courseProfessor = editCourseProfessor.getText().toString();

                if (!courseName.equals(event.getCourseName())) {
                    event.setCourseName(courseName);
                }
                if (!courseTime.equals(event.getCourseTime())) {
                    event.setCourseTime(courseTime);
                }
                if (!courseProfessor.equals(event.getCourseProfessor())) {
                    event.setCourseProfessor(courseProfessor);
                }
                break;
            case "Assignment":
                break;
            case "Exam":
                break;
        }
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_change);

    }
}
