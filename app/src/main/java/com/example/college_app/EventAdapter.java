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

public class EventAdapter extends ArrayAdapter<Event>  {

    private Button deleteButton;

    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0, events);
    }

    public void updateDataSet(List<Event> updatedList) {
        clear();
        addAll(updatedList);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        String eventTitle = "";

        if (event.getType() == 1) {
             eventTitle = event.getName() + " "  + event.getProfessor() + " " + event.getTime();
        } else if (event.getType() == 2) {
             eventTitle = event.getAssingment() + " "  + event.getDueDate() + " " + event.getCourse();
        } else if (event.getType() == 3) {
             eventTitle = event.getExam() + " "  + event.getTime2() + " " + event.getLocation();
        }

        eventCellTV.setText(eventTitle);

        Button deleteButton = convertView.findViewById(R.id.delete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = (int) getItemId(position);
                Event clickedEvent = getItem(clickedPosition);
                if (clickedEvent != null) {
                    if (clickedEvent.getType() == 1) {
                        Event.removeCourse(clickedEvent.getName(), clickedEvent.getProfessor(), clickedEvent.getTime(), clickedEvent.getDate());
                        updateDataSet(Event.eventsList);
                    } else if (clickedEvent.getType() == 2) {
                        Event.removeAssignment(clickedEvent.getAssingment(), clickedEvent.getDueDate(), clickedEvent.getCourse());
                        updateDataSet(Event.eventsList);
                    } else if (clickedEvent.getType() == 3) {
                        Event.removeExam(clickedEvent.getExam(), clickedEvent.getTime2(), clickedEvent.getLocation(), clickedEvent.getDate2());
                        updateDataSet(Event.eventsList);
                    }
                }
            }
        });

        return convertView;
    }




}
