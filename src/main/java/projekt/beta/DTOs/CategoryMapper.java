package projekt.beta.DTOs;

import org.springframework.stereotype.Service;
import projekt.beta.Entitys.Category;

@Service
public class CategoryMapper {
    public Long mapToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO.getId();
    }
}
