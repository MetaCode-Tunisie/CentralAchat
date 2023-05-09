package tn.esprit.centralpurchasing.Services;

import com.google.zxing.WriterException;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Entities.researches;

import java.io.IOException;
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
    //********************************************************************************
    public Product getProduct(Long idProduct) ;
    public byte[] generateQRCode(Long productId) throws WriterException, IOException;
    //*********************recommendation***********************
    public List<Product> getSimilarProducts(Long productId);

    public researches addResearch(Long id_user, researches r);
    public List<Product> SuggestedProducts(Long id_user);

}
