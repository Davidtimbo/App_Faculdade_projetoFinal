package com.example.faculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskDAO taskDAO;
    private ListView listViewTasks;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskDAO = new TaskDAO(this);
        listViewTasks = findViewById(R.id.listViewTasks);

        loadTasks();

        findViewById(R.id.buttonAddTask).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });

        // Configurar o click listener para itens da lista
        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
            intent.putExtra("TASK_ID", id);
            startActivity(intent);
        });

    }

    private void loadTasks() {
        List<Task> tasks = taskDAO.getAllTasks();
        taskAdapter = new TaskAdapter(this, tasks);
        listViewTasks.setAdapter(taskAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }
}
