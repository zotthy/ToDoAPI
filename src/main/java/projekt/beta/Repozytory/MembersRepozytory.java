package projekt.beta.Repozytory;

import org.springframework.data.repository.CrudRepository;
import projekt.beta.Entitys.Members;

import java.util.Optional;

public interface  MembersRepozytory extends CrudRepository<Members,Long> {
    Optional<Members> findByEmail(String email);
}
