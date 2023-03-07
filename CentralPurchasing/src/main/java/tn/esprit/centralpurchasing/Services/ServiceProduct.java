package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.*;
import tn.esprit.centralpurchasing.Repository.*;

import javax.persistence.EntityNotFoundException;
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
AccountRepository accountRepository;



    @Override
    public List<Product> searchProducts(String name, String adress, Double minPrice, Double maxPrice) {
        return productRepository.findBynameContainingAndAdressContainingAndPriceBetween(
                name, adress, minPrice, maxPrice);
    }

    @Override
    public Product addProductWithCategoryAndLocation(Long idAccount,Long idCategory, Long idLocation, Product product,Long idUnit) {
        Product savedProduct = productRepository.save(product);
        Account account = accountRepository.getReferenceById(idAccount);

        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + idCategory + " not found"));
        Location location = locationRepository.findById(idLocation)
                .orElseThrow(() -> new EntityNotFoundException("Location with id " + idLocation + " not found"));
        Unit unit = unitRepository.findById(idUnit)
                .orElseThrow(() -> new EntityNotFoundException("Unit with id " + idUnit + " not found"));
        //System.out.println("***************    HELLOO   *****************");
        if (product.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        product.setCategory(category);
        //System.out.println("***************    Set Category Done   *****************");
        product.setLocation(location);
        //System.out.println("***************    Set Location Done   *****************");

       // System.out.println("Unite :"+unit);
        product.setUnit(unit);
        product.setAccount(account);
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

