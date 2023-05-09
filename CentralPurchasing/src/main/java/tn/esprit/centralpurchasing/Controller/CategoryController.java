package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Services.IServiceCategory;
import tn.esprit.centralpurchasing.Services.ServiceProduct;

import java.util.List;


@RestController
@AllArgsConstructor
public class CategoryController {
    IServiceCategory serviceCategory;
ServiceProduct serviceProduct;

    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category) {

        return serviceCategory.addCategory(category);
    }
    @DeleteMapping("/deleteCategory/{idCategory}")
    void deleteCategory(@PathVariable Long idCategory){
        serviceCategory.deleteCategory(idCategory);
    }
    @GetMapping("/displayCategorys")
    List<Category> displayCategorys(){
        return serviceCategory.displayCategorys();
    }
    @PutMapping("/updateCategory/{idCategory}/{Name}")
    public void updateCategory(@PathVariable Long idCategory, @PathVariable String Name){
        serviceCategory.updateCategory(idCategory,Name);
    }


}


