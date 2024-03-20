package com.example.college_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class WeekViewActivity extends AppCompatActivity implements CalendarChange.OnItemListener, EditButtonClickListener, OnEventUpdateListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    private EditText eventNameEditText;
    private EventAdapter eventAdapter;

    public void onEditButtonClick(DailyEvent event) {

        // Handle edit button click here

        Intent intent = new Intent(WeekViewActivity.this, EventEditActivity.class);

        intent.putExtra("event", event);
        startActivityForResult(intent, 123456789);
        //startActivity(intent);
    }
    public void onEventUpdated(DailyEvent updatedEvent) {
        // Handle the updated event here
        // You can update your eventsList and refresh the UI if needed
        updateListView(DailyEvent.eventsList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123456789 && resultCode == RESULT_OK) {
            DailyEvent updatedEvent = data.getParcelableExtra("updated_event");
            // Update the event list and notify the adapter
            updateEvent(updatedEvent);
        }
    }
    private void updateEvent(DailyEvent updatedEvent) {
        // Find and update the corresponding event in the eventsList
        for (int i = 0; i < DailyEvent.eventsList.size(); i++) {
            if (DailyEvent.eventsList.get(i).getId().equals(updatedEvent.getId())) {
                DailyEvent.eventsList.set(i, updatedEvent);
                break;
            }
        }
        // Notify the adapter about the change
        eventAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        Spinner spinner = findViewById(R.id.sortSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();

                ArrayList<DailyEvent> filteredEvents = new ArrayList<>();

                if ("All".equals(selectedOption)) {
                    // Show all events
                    updateListView(DailyEvent.eventsList);

                } else if ("Assignments".equals(selectedOption)) {
                    for (DailyEvent event : DailyEvent.eventsList) {
                        if (event.getDailyEventType().equals("Assignment")) {
                            if (!filteredEvents.contains(event)) {
                                filteredEvents.add(event);
                            }
                        }
                    }
                    updateListView(filteredEvents);

                    // Show only assignment events
                } else if ("Date".equals(selectedOption)) {
                    for (DailyEvent event : DailyEvent.eventsList) {
                        if (event.getDailyEventType().equals("Assignment")) {
                            if (!filteredEvents.contains(event)) {
                                filteredEvents.add(event);
                            }
                        }
                    }

                    // Sort events by due date
                    filteredEvents.sort(new Comparator<DailyEvent>() {
                        @Override
                        public int compare(DailyEvent event1, DailyEvent event2) {
                            return event1.getAssignmentDate().compareTo(event2.getAssignmentDate());
                        }
                    });

                    updateListView(filteredEvents);

                } else if ("Course".equals(selectedOption)) {
                    for (DailyEvent event : DailyEvent.eventsList) {
                        if (event.getDailyEventType().equals("Assignment")) {
                            if (!filteredEvents.contains(event)) {
                                filteredEvents.add(event);
                            }
                        }
                    }
                    // Sort events by course
                    filteredEvents.sort(new Comparator<DailyEvent>() {
                        @Override
                        public int compare(DailyEvent event1, DailyEvent event2) {
                            return event1.getAssignmentCourseName().compareTo(event2.getAssignmentCourseName());
                        }
                    });

                    updateListView(filteredEvents);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV2);
        eventListView = findViewById(R.id.eventListView);
        eventAdapter = new EventAdapter(this, DailyEvent.eventsList, this);
        eventListView.setAdapter(eventAdapter);
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
        ArrayList<DailyEvent> dailyEvents = DailyEvent.eventsForDate(CalendarUtils.selectDate);

        eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents, this);
        eventListView.setAdapter(eventAdapter);
    }

    private void updateListView(ArrayList<DailyEvent> events) {
        eventAdapter = new EventAdapter(getApplicationContext(), events, this);
        //EventAdapter adapter = new EventAdapter(this, events, this);
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