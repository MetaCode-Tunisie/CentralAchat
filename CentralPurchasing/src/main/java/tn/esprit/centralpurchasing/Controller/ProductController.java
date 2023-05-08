package tn.esprit.centralpurchasing.Controller;

import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Entities.researches;
import tn.esprit.centralpurchasing.Services.IServiceProduct;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class ProductController {
    IServiceProduct serviceProduct;
    @PostMapping("/addProductAndCategoryAndlocation")
    public Product addProduct(@RequestParam Long idAccount, @RequestParam Long idCategory, @RequestParam Long idLocation, @RequestBody Product product, Long idUnit){

        return serviceProduct.addProductWithCategoryAndLocation(idAccount,idCategory,idLocation,product,idUnit);

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
//************************************************************************



    @GetMapping("/{productId}/qrcode")
    public void generateQRCode(@PathVariable Long productId, HttpServletResponse response) throws IOException, WriterException {
        byte[] qrCode = serviceProduct.generateQRCode(productId);

        response.setContentType("image/png");
        response.getOutputStream().write(qrCode);
    }
    @GetMapping("/{productId}/similar")
    public List<Product> getSimilarProducts(@PathVariable Long productId) {
        return serviceProduct.getSimilarProducts(productId);
    }
//    @GetMapping("/latest/{accountId}")
//    public ResponseEntity<List<Product>> getLatestProductsByAccount(@PathVariable Long accountId) {
//        List<Product> latestProducts = serviceProduct.getLatestProductsByAccount(accountId);
//        return ResponseEntity.ok(latestProducts);
//    }


    @PostMapping("/addResearch/{id_user}")
    @ResponseBody
    public researches addResearch(@PathVariable Long id_user, @RequestBody researches r) {
        return serviceProduct.addResearch(id_user, r);
    }
    @GetMapping("/SuggestedProducts/{id_user}")
    public List<Product> SuggestedProducts(@PathVariable Long id_user) {
        return serviceProduct.SuggestedProducts(id_user);
    }

}