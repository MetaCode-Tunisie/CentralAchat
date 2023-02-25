package tn.esprit.centralpurchasing.Services;

import org.springframework.data.jpa.domain.Specification;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Product;

import java.util.List;

public interface IServiceProduct {


    public Product addProductWithCategoryAndLocation(Long idCategory, Long idLocation, Product product,Long idUnit);
   // public Product AddProductAndCategoryAndLocation(Product product, Long idCategory ,Long idLocation);
    public List<Product> retrieveAllProducts();
    void deleteProduct(Long idProduct);
    public void updateProduct(Long idProduct, String Name);

}
