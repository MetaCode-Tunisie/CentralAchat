package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Services.IServiceProduct;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    IServiceProduct serviceProduct;
    @PostMapping("/addProductAndCategory")
    public Product addProduct(@RequestBody Product product, @RequestParam Long idCategory, @RequestParam Long idLocation){
        return serviceProduct.AddProductAndCategoryAndLocation(product,idCategory,idLocation);

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
