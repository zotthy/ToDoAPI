package projekt.beta.DTOs;

import org.springframework.stereotype.Service;
import projekt.beta.Entitys.Category;
import projekt.beta.Entitys.Task;
import projekt.beta.Repozytory.CategoryRepozytory;

@Service
public class TaskDTOMapper {

    private final CategoryMapper categoryMapper;

    public TaskDTOMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public TaskDTO mapToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDue_date(task.getDue_date());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setCategory(categoryMapper.mapToDTO(task.getCategory()));
        return taskDTO;
    }

    public Task mapToEntity(TaskRequest taskRequest, Category category) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDue_date(taskRequest.getDue_date());
        task.setPriority(taskRequest.getPriority());
        task.setStatus(taskRequest.getStatus());
        task.setCategory(category);
        return task;
    }
}
