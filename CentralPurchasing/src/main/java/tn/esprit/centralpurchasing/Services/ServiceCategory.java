package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Repository.CategoryRepository;
import tn.esprit.centralpurchasing.Repository.ProductRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class ServiceCategory implements IServiceCategory{
    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }

    @Override
    public List<Category> displayCategorys() {
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(Long idCategory, String Name) {
        // Récupération de la catégorie à modifier par son ID

        Category category= categoryRepository.findById(idCategory).get();

        // Modification du nom de la catégorie
        category.setName(Name);

        // Enregistrement de la catégorie modifiée
        categoryRepository.save(category);
    }



}



