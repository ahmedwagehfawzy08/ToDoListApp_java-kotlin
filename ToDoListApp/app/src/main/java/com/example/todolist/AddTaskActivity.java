package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editText = findViewById(R.id.editTextTask);
        dbHelper = new DatabaseHelper(this);
    }

    public void saveTask(View view) {
        String task = editText.getText().toString().trim();
        if (!task.isEmpty()) {
            dbHelper.addTask(task);
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please Enter a Task", Toast.LENGTH_SHORT).show();
        }
    }
}