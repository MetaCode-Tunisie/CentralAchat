package tn.esprit.centralpurchasing.Services;

import ch.qos.logback.core.status.Status;
import org.springframework.data.jpa.domain.Specification;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Product;

import java.util.List;
import java.util.Map;

public interface IServiceProduct {
    public List<Product> searchProducts(String name, String adress, Double minPrice, Double maxPrice);
    public Product addProductWithCategoryAndLocation(Long idAccount,Long idCategory, Long idLocation, Product product,Long idUnit);
   // public Product AddProductAndCategoryAndLocation(Product product, Long idCategory ,Long idLocation);
    public List<Product> retrieveAllProducts();
    void deleteProduct(Long idProduct);
    public void updateProduct(Long idProduct, String Name);
    public Map<String, Long> getProductCountsByStatus();

    //***************************************************************trie
   public List<Product> getAllProductsSortedByName();
    public List<Product> getAllProductsSortedByPrice();
}
