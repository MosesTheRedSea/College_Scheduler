package com.example.college_app;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;


public class CalendarView extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView dayOfMonth;
    private final CalendarChange.OnItemListener OnItemListener;

    public CalendarView(@NonNull View itemView, CalendarChange.OnItemListener onItemListener, ArrayList<LocalDate> days) {

        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.OnItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;
    }


    @Override
    public void onClick(View view) {
        OnItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
