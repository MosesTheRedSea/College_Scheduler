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
    private EditText position;
    private TextView title;
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
        position = findViewById(R.id.edit_text_position);
        loadTasksFromPrefs();
        title = findViewById(R.id.todo_list_title);
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
            title.setText("To Do List");
        }


        public void editItem(View view) {


                String compare = "Input(a number) which event you want to change";
                String posText = String.valueOf(position.getText());
                if (toDoList.size() == 0) {
                    title.setText("There are no events!!!!!");
                } else if (posText.isEmpty()) {
                    title.setText("THERE IS NOTHING IN THE IPNUT !!!!!");
                } else if (compare.equals(posText)) {
                    title.setText("YOU HAVE TO INPUT A POSITION!!!!");
                } else if (posText.matches(".*[a-zA-Z].*")) {
                    title.setText("YOU HAVE TO INPUT A NUMBER");
                } else {
                    int pos = Integer.parseInt(posText) - 1;
                    if (pos > toDoList.size()) {
                        title.setText("The input is bigger than the the # of events");
                    } else {
                        toDoList.set(pos, editText.getText().toString().trim());
                        arrayAdapter.notifyDataSetChanged();
                        saveTasksToPrefs();
                        editText.setText("");
                        title.setText("To Do List");
                    }
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
