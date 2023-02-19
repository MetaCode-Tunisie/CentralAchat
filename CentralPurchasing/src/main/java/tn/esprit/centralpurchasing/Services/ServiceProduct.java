package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Location;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;
import tn.esprit.centralpurchasing.Repository.CategoryRepository;
import tn.esprit.centralpurchasing.Repository.LocationRepository;
import tn.esprit.centralpurchasing.Repository.ProductPhotoRepository;
import tn.esprit.centralpurchasing.Repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceProduct implements IServiceProduct {
ProductRepository productRepository;
CategoryRepository categoryRepository;
LocationRepository locationRepository;
ProductPhotoRepository productPhotoRepository;



  //  public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
     //   EtudiantRepository.save(e);
      //  e.getEquipes().add(equipeRepository.findById(idEquipe).orElse(null));
      //  contratRepository.findById(idContrat).orElse(null).setEtudiants(e);
      //  EtudiantRepository.save(e);
      //  contratRepository.save(contratRepository.findById(idContrat).orElse(null));
       // return e;
    //******************************************
 // Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
   // Location location = locationRepository.findById(locationId).orElseThrow(() -> new ResourceNotFoundException("Location not found"));

   // Product product = new Product();
        //product.setName(name);
       // product.setPrice(price);
       // product.setCategory(category);
       // product.setLocation(location);

       // productRepository.save(product);

       // return product;

    @Override
    public Product AddProductAndCategoryAndLocationAndPhoto(Product product, Long idCategory , Long idLocation , Long idProductPhoto) {
        Category category = categoryRepository.findById(idCategory).orElse(null);
        Location location = locationRepository.findById(idLocation).orElse(null);
        ProductPhoto productPhoto = productPhotoRepository.findById(idProductPhoto).orElse(null);
return null;
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public void updateProduct(Long idProduct, String Name) {
Product product =productRepository.findById(idProduct).get();
product.setName(Name);
productRepository.save(product);
    }
}
