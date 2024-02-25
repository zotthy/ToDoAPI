package projekt.beta.Repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import projekt.beta.Entitys.UserRole;

import java.util.Optional;

public interface UserRoleRepozytory extends JpaRepository<UserRole,Long> {
    Optional<UserRole> findByName(String name);
}
