package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Product;

import java.util.List;

public interface IServiceProduct {
    public Product AddProductAndCategoryAndLocationAndPhoto(Product product, Long idCategory ,Long idLocation,Long idProductPhoto);
    public List<Product> retrieveAllProducts();
    void deleteProduct(Long idProduct);
    public void updateProduct(Long idProduct, String Name);

}
