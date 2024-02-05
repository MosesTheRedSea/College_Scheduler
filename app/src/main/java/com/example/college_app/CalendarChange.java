package com.example.college_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

class CalendarChange extends RecyclerView.Adapter<CalendarView> {

    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;
    public CalendarChange(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if (days.size() > 15) {  // month view
           layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        } else {
            layoutParams.height = (int) parent.getHeight();
        }

        return new CalendarView(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarView holder, int position) {
        final LocalDate date = days.get(position);
        if(date == null) {
            holder.dayOfMonth.setText(" ");
        } else {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if (date.equals(CalendarUtils.selectDate)) {
                holder.parentView.setBackgroundColor(Color.rgb(85, 176, 230));
            }
        }

    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);

    }
}
