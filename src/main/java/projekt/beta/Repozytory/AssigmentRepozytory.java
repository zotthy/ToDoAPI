package projekt.beta.Repozytory;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import projekt.beta.Entitys.Assignment;

import java.awt.print.Pageable;
import java.util.List;

public interface AssigmentRepozytory extends JpaRepository<Assignment,Long> {
    List<Assignment> findByUserId(Long memberId);
    List<Assignment> findByUserIdAndTaskId(Long memberId,Long taskId);
}
