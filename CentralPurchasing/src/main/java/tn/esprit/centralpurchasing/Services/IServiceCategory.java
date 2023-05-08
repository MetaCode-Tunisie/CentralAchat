package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Category;

import java.util.List;

public interface IServiceCategory {
    Category addCategory(Category category);
    void deleteCategory(Long idCategory);
    List<Category> displayCategorys();
    public void updateCategory(Long idCategory, String Name);


}
