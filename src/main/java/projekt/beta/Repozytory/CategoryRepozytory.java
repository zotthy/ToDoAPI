package projekt.beta.Repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import projekt.beta.Entitys.Category;

public interface CategoryRepozytory extends JpaRepository<Category,Long> {
}
