package org.example.taskapplication.doa;

import org.example.taskapplication.model.Task;
import java.util.List;

public interface TaskDAO {
    void createTask(Task task);
    Task getTaskById(int id);
    List<Task> getAllTasks();
    void updateTask(Task task);
    void deleteTask(Task task);
    void DueDateTask (Task task);
}
