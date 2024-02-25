package projekt.beta.Repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import projekt.beta.Entitys.Members;

import java.util.Optional;

public interface  MembersRepozytory extends JpaRepository<Members,Long> {
    Optional<Members> findByEmail(String email);
    boolean existsByEmail(String email);
}
