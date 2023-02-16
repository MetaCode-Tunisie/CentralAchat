package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Repository.CategoryRepository;
@Service
@AllArgsConstructor
public class ServiceCategory implements IServiceCategory{
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
