package projekt.beta.Repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.beta.DTOs.TaskDTO;
import projekt.beta.Entitys.Task;

import java.util.List;

public interface TaskRepozytory extends JpaRepository<Task,Long> {
    List<Task> findByTitleContainingIgnoreCase(String title);
}
