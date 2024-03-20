package com.example.college_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.DialogInterface;
import java.util.List;

public class EventAdapter extends ArrayAdapter<DailyEvent>  {

    private Button deleteButton;
    private EditButtonClickListener editButtonClickListener;


    public EventAdapter(@NonNull Context context, List<DailyEvent> events, EditButtonClickListener listener) {
        super(context, 0, events);
        this.editButtonClickListener = listener;
    }

    public void updateDataSet(List<DailyEvent> updatedList) {
        clear();
        addAll(updatedList);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DailyEvent event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }


        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        String eventTitle = "";

        assert event != null;

        if (event.getDailyEventType().equals("Course")) {
            eventTitle = event.getCourseName() + " " + event.getCourseProfessor() + " " + event.getCourseTime();
        } else if (event.getDailyEventType().equals("Assignment")) {
            eventTitle = event.getAssignmentName() + " " + event.getAssignmentCourseName();
        } else if (event.getDailyEventType().equals("Exam")) {
            eventTitle = event.getExam() + " " + event.getExamTime() + " " + event.getExamLocation();
        }

        eventCellTV.setText(eventTitle);

        Button editButton = convertView.findViewById(R.id.editCellBTN);
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                // The Position that was Clicked
                DailyEvent clickedEvent = DailyEvent.eventsList.get(position);
                if (editButtonClickListener != null && clickedEvent != null) {
                    editButtonClickListener.onEditButtonClick(clickedEvent);
                }
            }
        });


        // Delete Button in Event Cell
        Button deleteButton = convertView.findViewById(R.id.delete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyEvent clickedEvent = DailyEvent.eventsList.get(position);

                if (clickedEvent != null) {
                    DailyEvent.removeEvent(clickedEvent);
                    updateDataSet(DailyEvent.eventsList);
                }
            }
        });

        return convertView;
    }




}
