package com.example.faculdade;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextDescription, editTextPriority, editTextDueDate;
    private Button buttonSave, buttonCancel;
    private TaskDAO taskDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPriority = findViewById(R.id.editTextPriority);
        editTextDueDate = findViewById(R.id.editTextDueDate);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel); // Adicionado para o botão de cancelar

        taskDAO = new TaskDAO(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                int priority = Integer.parseInt(editTextPriority.getText().toString());
                String dueDate = editTextDueDate.getText().toString();

                Task task = new Task(0, title, description, priority, dueDate, false);
                taskDAO.addTask(task);

                Toast.makeText(AddTaskActivity.this, "Tarefa adicionada com sucesso", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a atividade após salvar a tarefa
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a atividade sem salvar
            }
        });
    }
}
