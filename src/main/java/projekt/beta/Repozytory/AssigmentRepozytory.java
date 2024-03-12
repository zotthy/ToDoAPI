package projekt.beta.Repozytory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projekt.beta.Entitys.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssigmentRepozytory extends JpaRepository<Assignment,Long> {
    Page<Assignment> findByUserId(Long memberId ,Pageable pageable);
    List<Assignment> findByUserIdAndTaskId(Long memberId,Long taskId);
}
