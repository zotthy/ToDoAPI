package projekt.beta.Repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.beta.Entitys.Task;

public interface TaskRepozytory extends JpaRepository<Task,Long> {
}
