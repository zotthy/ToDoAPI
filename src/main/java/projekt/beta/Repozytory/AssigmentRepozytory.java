package projekt.beta.Repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.beta.Entitys.Assignment;

public interface AssigmentRepozytory extends JpaRepository<Assignment,Long> {
}
