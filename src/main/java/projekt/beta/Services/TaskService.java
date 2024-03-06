package projekt.beta.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projekt.beta.DTOs.TaskDTO;
import projekt.beta.DTOs.TaskDTOMapper;
import projekt.beta.DTOs.TaskRequest;
import projekt.beta.Entitys.Assignment;
import projekt.beta.Entitys.Category;
import projekt.beta.Entitys.Members;
import projekt.beta.Entitys.Task;
import projekt.beta.Repozytory.AssigmentRepozytory;
import projekt.beta.Repozytory.CategoryRepozytory;
import projekt.beta.Repozytory.MembersRepozytory;
import projekt.beta.Repozytory.TaskRepozytory;
import projekt.beta.Security.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepozytory taskRepozytory;
    private final TaskDTOMapper taskDTOMapper;

    private final CategoryRepozytory categoryRepozytory;

    private final JwtService jwtService;

    private final MembersService membersService;
    private final MembersRepozytory membersRepozytory;

    private final AssigmentRepozytory assigmentRepozytory;

    public TaskService(TaskRepozytory taskRepozytory, TaskDTOMapper taskDTOMapper,
                       CategoryRepozytory categoryRepozytory, JwtService jwtService, MembersService membersService,
                       MembersRepozytory membersRepozytory, AssigmentRepozytory assigmentRepozytory) {
        this.taskRepozytory = taskRepozytory;
        this.taskDTOMapper = taskDTOMapper;
        this.categoryRepozytory = categoryRepozytory;
        this.jwtService = jwtService;
        this.membersService = membersService;
        this.membersRepozytory = membersRepozytory;
        this.assigmentRepozytory = assigmentRepozytory;
    }

    private String getUserEmail(String userJwtToken) {
        String actualToken = userJwtToken.replace("Bearer ", "");
        String userEmail = jwtService.getEmailFromToken(actualToken);
        return userEmail;
    }

    public TaskDTO addTaskWithExistingCategory(TaskRequest taskRequest, String userJwtToken) {
        Category existingCategory = categoryRepozytory.findById(taskRequest.getCategory_id())
                .orElseThrow(() -> new RuntimeException("Category with ID " + taskRequest.getCategory_id() + " not found."));
        String userEmail = getUserEmail(userJwtToken);
        Long memberId = membersService.getIdByEmail(userEmail);

        Task task = taskDTOMapper.mapToEntity(taskRequest, existingCategory);
        Task savedTask = taskRepozytory.save(task);

        Assignment assignment = new Assignment();
        assignment.setTask(savedTask);
        assignment.setUser(membersRepozytory.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + memberId + " not found.")));

        assigmentRepozytory.save(assignment);
        return taskDTOMapper.mapToDTO(savedTask);
    }

    public List<TaskDTO> getTasksByUserAndId(Long taskId, String userJwtToken) {
        String actualToken = userJwtToken.replace("Bearer ", "");
        String userEmail = jwtService.getEmailFromToken(actualToken);
        Optional<Members> memberOpt = membersService.getMemberByEmail(userEmail);

        if (!memberOpt.isPresent()) {
            throw new RuntimeException("User not found!");
        }

        Long memberId = memberOpt.get().getId();
        List<Assignment> assignments = assigmentRepozytory.findByUserIdAndTaskId(memberId, taskId);

        return assignments.stream()
                .map(Assignment::getTask)
                .map(taskDTOMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public Page<TaskDTO> getTasksByUser(String userJwtToken, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String userEmail = getUserEmail(userJwtToken);

        Optional<Members> memberOpt = membersService.getMemberByEmail(userEmail);

        if (!memberOpt.isPresent()) {
            throw new RuntimeException("User not found!");
        }

        Long memberId = memberOpt.get().getId();

        Page<Assignment> assignments = assigmentRepozytory.findByUserId(memberId,pageable);
        return assignments.map(assignment -> taskDTOMapper.mapToDTO(assignment.getTask()));
    }

}
