package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Services.IServiceCategory;

@RestController
@AllArgsConstructor
public class CategoryController {
    IServiceCategory serviceCategory;
    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category){

        return serviceCategory.addCategory(category);
    }
}
