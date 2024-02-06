package com.example.college_app;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class toDoList extends AppCompatActivity {
    private ArrayList<String> toDoList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private EditText editText;
    private static final String PREFS_NAME = "TodoListPrefs";

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
                String removedItem = toDoList.remove(position);
                // Update the adapter
                arrayAdapter.notifyDataSetChanged();
                saveTasksToPrefs(); // Save the updated list
            }

        });
        editText = findViewById(R.id.id_edit_text);

        loadTasksFromPrefs();
    }

        public void addItemToList(View view) {
            String newItem = editText.getText().toString().trim();
            if (!newItem.isEmpty()) {
                toDoList.add(newItem);
                arrayAdapter.notifyDataSetChanged();
                saveTasksToPrefs(); // Save the updated list
                editText.setText("");
                System.out.println("This button has been clicked");
            }
        }

    private void saveTasksToPrefs() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> taskSet = new HashSet<>(toDoList);
        editor.putStringSet("taskSet", taskSet);
        editor.apply();
    }

    private void loadTasksFromPrefs() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> taskSet = preferences.getStringSet("taskSet", new HashSet<>());
        toDoList.addAll(taskSet);
        arrayAdapter.notifyDataSetChanged();
    }
}
