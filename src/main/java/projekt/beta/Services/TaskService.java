package projekt.beta.Services;

import org.springframework.stereotype.Service;
import projekt.beta.DTOs.TaskDTO;
import projekt.beta.DTOs.TaskDTOMapper;
import projekt.beta.Entitys.Task;
import projekt.beta.Repozytory.TaskRepozytory;

@Service
public class TaskService {
    private final TaskRepozytory taskRepozytory;
    private final TaskDTOMapper taskDTOMapper;

    public TaskService(TaskRepozytory taskRepozytory, TaskDTOMapper taskDTOMapper) {
        this.taskRepozytory = taskRepozytory;
        this.taskDTOMapper = taskDTOMapper;
    }

    public TaskDTO saveTask(TaskDTO taskDTO){
        Task dataMapper = taskDTOMapper.map(taskDTO);
        Task savedata = taskRepozytory.save(dataMapper);
        return taskDTOMapper.map(savedata);
    }

}
