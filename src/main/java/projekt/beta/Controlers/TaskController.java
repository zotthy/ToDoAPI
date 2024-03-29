package projekt.beta.Controlers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt.beta.DTOs.TaskDTO;
import projekt.beta.DTOs.TaskRequest;
import projekt.beta.Security.JwtService;
import projekt.beta.Services.TaskService;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;
    private final JwtService jwtService;

    public TaskController(TaskService taskService, JwtService jwtService) {
        this.taskService = taskService;
        this.jwtService = jwtService;
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskRequest taskRequest,
                                            @RequestHeader("Authorization") String token) {
        TaskDTO createTask = taskService.addTaskWithExistingCategory(taskRequest, token);
        return new ResponseEntity<>(createTask, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> display(@RequestHeader("Authorization") String userJwtToken,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        Page<TaskDTO> pageResult = taskService.getTasksByUser(userJwtToken, page, size);

        return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<List<TaskDTO>> getById(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String token) {
        List<TaskDTO> taskDTOList = taskService.getTasksByUserAndId(id, token);
        if (taskDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(taskDTOList, HttpStatus.OK);
        }
    }
}
