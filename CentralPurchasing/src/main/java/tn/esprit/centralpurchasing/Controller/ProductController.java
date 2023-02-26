package tn.esprit.centralpurchasing.Controller;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Repository.ProductRepository;
import tn.esprit.centralpurchasing.Services.IServiceProduct;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ProductController {
    IServiceProduct serviceProduct;
    @PostMapping("/addProductAndCategoryAndlocation")
    public Product addProduct(@RequestBody Product product, @RequestParam Long idCategory, @RequestParam Long idLocation, @RequestParam Long idUnit){
        return serviceProduct.addProductWithCategoryAndLocation(idCategory,idLocation,product,idUnit);

    }
    @GetMapping("/displayProducts")
    public List<Product> retrieveAllProducts(){
        return serviceProduct.retrieveAllProducts();
    }
@DeleteMapping("/deleteProduct")
    public void deleteProduct(Long idProduct){
        serviceProduct.deleteProduct(idProduct);
    }
    @PutMapping("/updateProduct/{idProduct}/{Name}")
    public void updateProduct(Long idProduct, String Name){
        serviceProduct.updateProduct(idProduct,Name);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String adress,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
            ) {
        List<Product> products = serviceProduct.searchProducts(name, adress, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getProductCountsByStatus() {
        Map<String, Long> counts = serviceProduct.getProductCountsByStatus();
        return ResponseEntity.ok(counts);
    }
    @GetMapping("/sortedByName")
    public List<Product> getAllProductsSortedByName() {
        return serviceProduct.getAllProductsSortedByName();
    }

    @GetMapping("/sortedByPrice")
    public List<Product> getAllProductsSortedByPrice() {
        return serviceProduct.getAllProductsSortedByPrice();
    }

}
