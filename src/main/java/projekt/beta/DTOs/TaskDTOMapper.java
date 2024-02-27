package projekt.beta.DTOs;

import org.springframework.stereotype.Service;
import projekt.beta.Entitys.Task;

@Service
public class TaskDTOMapper {
    public TaskDTO map(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setCategory(task.getCategory());
        return taskDTO;
    }

    public Task map(TaskDTO taskDTO){
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());
        task.setCategory(taskDTO.getCategory());
        return task;
    }
}
