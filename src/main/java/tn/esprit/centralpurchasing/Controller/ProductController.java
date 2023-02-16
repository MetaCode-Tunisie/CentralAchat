package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Services.IServiceProduct;

@RestController
@AllArgsConstructor
public class ProductController {
    IServiceProduct serviceProduct;
    @PostMapping("/addProductAndCategory")
    public Product addProduct(@RequestBody Product product, @RequestParam Long idCategory){
        return serviceProduct.ajouterProduitAvecCategorie(product, idCategory);

    }

}
