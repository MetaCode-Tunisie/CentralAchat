package tn.esprit.centralpurchasing.Services;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.*;
import tn.esprit.centralpurchasing.Repository.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class ServiceProduct implements IServiceProduct {
ProductRepository productRepository;
CategoryRepository categoryRepository;
LocationRepository locationRepository;
ProductPhotoRepository productPhotoRepository;
UnitRepository unitRepository;


    @Override
    public List<Product> searchProducts(String name, String adress, Double minPrice, Double maxPrice) {
        return productRepository.findBynameContainingAndAdressContainingAndPriceBetween(
                name, adress, minPrice, maxPrice);
    }

    @Override
    public Product addProductWithCategoryAndLocation(Long categoryId, Long locationId, Product product,Long idUnit) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location with id " + locationId + " not found"));
        Unit unit = unitRepository.findById(idUnit)
                .orElseThrow(() -> new EntityNotFoundException("Unit with id " + idUnit + " not found"));
        //System.out.println("***************    HELLOO   *****************");
        product.setCategory(category);
        //System.out.println("***************    Set Category Done   *****************");
        product.setLocation(location);
        //System.out.println("***************    Set Location Done   *****************");

       // System.out.println("Unite :"+unit);
        product.setUnit(unit);
        return productRepository.save(product);
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public void updateProduct(Long idProduct, String Name) {
Product product =productRepository.findById(idProduct).get();
product.setName(Name);
productRepository.save(product);
    }

    @Override
    public Map<String, Long> getProductCountsByStatus() {
        List<Object[]> counts = productRepository.countProductsByStatus();
        Map<String, Long> result = new HashMap<>();
        for (Object[] row : counts) {
            String status = row[0].toString();
            Long count = (Long) row[1];
            result.put(status, count);
        }
        return result;
    }

    @Override
    public List<Product> getAllProductsSortedByName() {
        return productRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<Product> getAllProductsSortedByPrice() {
        return productRepository.findAll(Sort.by("price"));
    }


}

