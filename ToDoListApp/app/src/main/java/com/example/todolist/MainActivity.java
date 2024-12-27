package com.example.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper dbHelper;
    private ArrayList<String> tasks;
    private ArrayList<Integer> taskIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        loadTasks();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dbHelper.deleteTask(taskIds.get(position));
                Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                loadTasks();
                return true;
            }
        });
    }

    public void addTask(View view) {
        startActivity(new Intent(this, AddTaskActivity.class));
    }

    private void loadTasks() {
        tasks = new ArrayList<>();
        taskIds = new ArrayList<>();

        Cursor cursor = dbHelper.getTasks();
        if (cursor.moveToFirst()) {
            do {
                taskIds.add(cursor.getInt(0));
                tasks.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);
    }
}