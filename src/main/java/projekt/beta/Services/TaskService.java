package projekt.beta.Services;

import org.springframework.stereotype.Service;
import projekt.beta.DTOs.TaskDTO;
import projekt.beta.DTOs.TaskDTOMapper;
import projekt.beta.DTOs.TaskRequest;
import projekt.beta.Entitys.Category;
import projekt.beta.Entitys.Task;
import projekt.beta.Repozytory.CategoryRepozytory;
import projekt.beta.Repozytory.TaskRepozytory;

@Service
public class TaskService {
    private final TaskRepozytory taskRepozytory;
    private final TaskDTOMapper taskDTOMapper;

    private final CategoryRepozytory categoryRepozytory;

    public TaskService(TaskRepozytory taskRepozytory, TaskDTOMapper taskDTOMapper, CategoryRepozytory categoryRepozytory) {
        this.taskRepozytory = taskRepozytory;
        this.taskDTOMapper = taskDTOMapper;
        this.categoryRepozytory = categoryRepozytory;
    }

    public TaskDTO addTaskWithExistingCategory(TaskRequest taskRequest) {
        Category existingCategory = categoryRepozytory.findById(taskRequest.getCategory_id())
                .orElseThrow(() -> new RuntimeException("Category with ID " + taskRequest.getCategory_id() + " not found."));

        Task task = taskDTOMapper.mapToEntity(taskRequest, existingCategory);

        Task savedTask = taskRepozytory.save(task);

        return taskDTOMapper.mapToDTO(savedTask);
    }

}
