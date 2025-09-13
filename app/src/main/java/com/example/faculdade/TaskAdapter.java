package com.example.faculdade;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private List<Task> tasks;
    private TaskDAO taskDAO;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
        this.tasks = tasks;
        this.taskDAO = new TaskDAO(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        }

        Task task = tasks.get(position);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewDescription = convertView.findViewById(R.id.textViewDescription);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewTitle.setText(task.getTitle());
        textViewDescription.setText(task.getDescription());

        // Configurar o click listener para editar a tarefa
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditTaskActivity.class);
            intent.putExtra("TASK_ID", task.getId());
            context.startActivity(intent);
        });

        // Configurar o click listener para excluir a tarefa
        buttonDelete.setOnClickListener(v -> {
            taskDAO.deleteTask(task.getId());
            tasks.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
