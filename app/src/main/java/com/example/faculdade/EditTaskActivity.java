package com.example.faculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextDescription, editTextPriority, editTextDueDate;
    private Button buttonSave, buttonCancel;  // Adicione buttonCancel aqui
    private TaskDAO taskDAO;
    private long taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Inicializar views
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPriority = findViewById(R.id.editTextPriority);
        editTextDueDate = findViewById(R.id.editTextDueDate);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);  // Inicialize o botão "Cancelar"


        // Inicializar TaskDAO
        taskDAO = new TaskDAO(this);

        // Obter ID da tarefa da Intent
        taskId = getIntent().getLongExtra("TASK_ID", -1);

        // Verificar se o ID é válido e carregar os dados da tarefa
        if (taskId != -1) {
            loadTaskData(taskId);
        } else {
            Toast.makeText(this, "ID de tarefa inválido", Toast.LENGTH_SHORT).show();
            finish(); // Fecha a atividade se o ID for inválido
        }

        // Configurar listener para o botão "Salvar"
        buttonSave.setOnClickListener(v -> saveTask());

        // Configurar listener para o botão "Cancelar"
        buttonCancel.setOnClickListener(v -> finish());
    }

    private void loadTaskData(long taskId) {
        Task task = taskDAO.getTaskById(taskId);
        if (task != null) {
            // Preencher os campos com os dados da tarefa
            editTextTitle.setText(task.getTitle());
            editTextDescription.setText(task.getDescription());
            editTextPriority.setText(String.valueOf(task.getPriority()));
            editTextDueDate.setText(task.getDueDate());
        } else {
            Toast.makeText(this, "Tarefa não encontrada", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveTask() {
        try {
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();
            int priority = Integer.parseInt(editTextPriority.getText().toString().trim());
            String dueDate = editTextDueDate.getText().toString().trim();

            // Verificar se todos os campos estão preenchidos corretamente
            if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty()) {
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Criar a tarefa com os dados atualizados
            Task task = new Task(taskId, title, description, priority, dueDate, false);
            int rowsAffected = taskDAO.updateTask(task);

            if (rowsAffected > 0) {
                Toast.makeText(this, "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao atualizar a tarefa", Toast.LENGTH_SHORT).show();
            }

            finish(); // Fecha a atividade após salvar a tarefa
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Prioridade inválida. Digite um número entre 1 e 5", Toast.LENGTH_SHORT).show();
        }
    }
}
