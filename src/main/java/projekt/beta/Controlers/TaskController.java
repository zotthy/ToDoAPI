package projekt.beta.Controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projekt.beta.DTOs.TaskDTO;
import projekt.beta.DTOs.TaskRequest;
import projekt.beta.Repozytory.TaskRepozytory;
import projekt.beta.Services.TaskService;

import java.net.URI;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskRequest taskRequest){
        TaskDTO createTask = taskService.addTaskWithExistingCategory(taskRequest);
        return new ResponseEntity<>(createTask,HttpStatus.CREATED);
    }
}
