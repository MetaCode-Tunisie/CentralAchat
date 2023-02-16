package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Repository.CategoryRepository;
import tn.esprit.centralpurchasing.Repository.ProductRepository;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ServiceProduct implements IServiceProduct {
ProductRepository productRepository;
CategoryRepository categoryRepository;


    @Override
    public Product ajouterProduitAvecCategorie(Product product, Long idCategory) {
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new EntityNotFoundException("Catégory not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }
}
