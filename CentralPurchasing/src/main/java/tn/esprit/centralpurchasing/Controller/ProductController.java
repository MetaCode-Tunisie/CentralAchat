package tn.esprit.centralpurchasing.Controller;

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
}
