package com.example.college_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarChange.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV2);
        eventListView = findViewById(R.id.eventListView);

        setWeekView();
    }

    private void setWeekView() {
        monthYearText.setText(CalendarUtils.monthYearFromDate(CalendarUtils.selectDate));
        ArrayList<LocalDate> days = CalendarUtils.daysInWeekArray(CalendarUtils.selectDate);

        CalendarChange calendarChange = new CalendarChange(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarChange);
        setEventAdapter();

    }

    public void previousWeekAction(View view) {
        CalendarUtils.selectDate = CalendarUtils.selectDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectDate = CalendarUtils.selectDate.plusWeeks(1);
        setWeekView();
    }

    protected void onResume() {
        super.onResume();
        setEventAdapter();
    }

    public void setEventAdapter() {
        Log.d("debug", CalendarUtils.selectDate.toString());
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectDate);

        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectDate = date;
        setWeekView();
    }

    public void newEventAction(View view) {
        String ButtonClicked = "Course button";
        Intent intent = new Intent(this, EventEditActivity.class);
        intent.putExtra("button_clicked", ButtonClicked);
        startActivity(intent);
    }

    public void newExamAction(View view) {
        String ButtonClicked = "Exam button";
        Intent intent = new Intent(this, EventEditActivity.class);
        intent.putExtra("button_clicked", ButtonClicked);
        startActivity(intent);
    }

    public void newAssignAction(View view) {
        String ButtonClicked = "Assign button";
        Intent intent = new Intent(this, EventEditActivity.class);
        intent.putExtra("button_clicked", ButtonClicked);
        startActivity(intent);
    }





}