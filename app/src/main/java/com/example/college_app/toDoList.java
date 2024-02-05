package com.example.college_app;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class toDoList extends AppCompatActivity {


    ArrayList<String> toDoList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        toDoList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.todo_list,toDoList);


        listView = findViewById(R.id.id_list_view);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

        });
        editText = findViewById(R.id.id_edit_text);
    }

        public void addItemToList(View view) {
            toDoList.add(editText.getText().toString());
            arrayAdapter.notifyDataSetChanged();
            System.out.println("This button has been clicked");
            editText.setText("");
        }
}
