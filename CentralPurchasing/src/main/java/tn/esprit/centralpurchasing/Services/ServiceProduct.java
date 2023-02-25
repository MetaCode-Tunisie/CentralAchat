package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.*;
import tn.esprit.centralpurchasing.Repository.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceProduct implements IServiceProduct {
ProductRepository productRepository;
CategoryRepository categoryRepository;
LocationRepository locationRepository;
ProductPhotoRepository productPhotoRepository;
UnitRepository unitRepository;


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
    public Product addProductWithCategoryAndLocation(Long categoryId, Long locationId, Product product,Long idUnit) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location with id " + locationId + " not found"));
        Unit unit = unitRepository.findById(idUnit)
                .orElseThrow(() -> new EntityNotFoundException("Unit with id " + idUnit + " not found"));
        //System.out.println("***************    HELLOO   *****************");
        product.setCategory(category);
        //System.out.println("***************    Set Category Done   *****************");
        product.setLocation(location);
        //System.out.println("***************    Set Location Done   *****************");

       // System.out.println("Unite :"+unit);
        product.setUnit(unit);
        return productRepository.save(product);
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
